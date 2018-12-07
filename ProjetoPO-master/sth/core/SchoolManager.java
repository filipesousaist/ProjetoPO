package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.DuplicateProjectIdException;

import sth.core.exception.survey.CoreOpeningSurveyException;
import sth.core.exception.survey.CoreClosingSurveyException;
import sth.core.exception.survey.CoreFinishingSurveyException;
import sth.core.exception.survey.CoreDuplicateSurveyException;
import sth.core.exception.survey.CoreSurveyFinishedException;
import sth.core.exception.survey.CoreNonEmptySurveyException;
import sth.core.exception.survey.CoreNoSurveyException;

import java.util.Collection;
import java.util.List;

import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * The façade class
 */
public class SchoolManager {

	private School _school;
	private Person _loggedUser;
	private String _serialFilename;

	public SchoolManager() {
		_school = new School("Universidade");
	}
	
	/**
	 * @param datafile
	 * @throws ImportFileException
	 * @throws InvalidCourseSelectionException
	 */
	public void importFile(String datafile) throws ImportFileException {
		try {
			_school.importFile(datafile);
		} 
		catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
		}
	}

	public String getSerialFilename() {
		return _serialFilename;
	}

	public void setSerialFilename(String fn) {
		_serialFilename = fn;
	}

	public School getSchool() {
		return _school;
	}

	public void updateSchool(School newSchool) throws NoSuchPersonIdException {
		int userId = _loggedUser.getId();
		if (! newSchool.idExists(userId))
			throw new NoSuchPersonIdException(userId);
		_school = newSchool;
		login(userId);
	}

	/**
	 * Do the login of the user with the given identifier.

	 * @param id identifier of the user to login
	 * @throws NoSuchPersonIdException if there is no users with the given identifier
	 */
	public void login(int id) throws NoSuchPersonIdException {
		_loggedUser = _school.getPerson(id);
	}

	public List<Notification> getNotifications() {
		return _loggedUser.popNotifications();
	}

	/**
	 * @return true when the currently logged in person is an administrative
	 */
	public boolean isLoggedUserAdministrative() {
		return _loggedUser.getPersonType() == PersonType.EMPLOYEE;
	}

	/**
	 * @return true when the currently logged in person is a professor
	 */
	public boolean isLoggedUserProfessor() {
		return _loggedUser.getPersonType() == PersonType.TEACHER;
	}

	/**
	 * @return true when the currently logged in person is a student
	 */
	public boolean isLoggedUserStudent() {
		return _loggedUser.getPersonType() == PersonType.STUDENT;
	}

	/**
	 * @return true when the currently logged in person is a representative
	 */
	public boolean isLoggedUserRepresentative() {
		return isLoggedUserStudent() && 
			((Student) _loggedUser).isRepresentative();
	}

	public boolean idExists(int id) {
		return _school.idExists(id);
	}

	public Person getLoggedUser() {
		return _loggedUser;
	}

	public void changePhoneNumber(String newPhoneNumber) {
		_loggedUser.changePhoneNumber(newPhoneNumber);
	}
	
	public Collection<Person> getAllUsers() {
		return _school.getAllUsers();
	}

	public Collection<Person> getAllUsers(String string) {
		return _school.getAllUsers(string);
	}

	public void createProject(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, DuplicateProjectIdException {

		((Teacher) _loggedUser).createProject(disciplineName, projectName);
	}

	public void closeProject(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException {

		((Teacher) _loggedUser).closeProject(disciplineName, projectName);
	}

	public Collection<Student> getStudents(String disciplineName) 
		throws NoSuchDisciplineIdException {
			
		return ((Teacher) _loggedUser).getStudents(disciplineName);
	}

	public Project getProject(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException {

		return ((Teacher) _loggedUser).getProject(disciplineName, projectName);
	}

	public void deliverProject(
		String disciplineName, String projectName, String message) 
		throws NoSuchDisciplineIdException, NoSuchProjectIdException {

		((Student) _loggedUser).deliverProject(
			disciplineName, projectName, message);
	}

	public void answerSurvey(
		String disciplineName, String projectName, int hours, String message)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException {

			((Student) _loggedUser).answerSurvey(
				disciplineName, projectName, hours, message);
	}

	public String getSurveyResults(String disciplineName, 
		String projectName) throws NoSuchDisciplineIdException,
		NoSuchProjectIdException, CoreNoSurveyException {

		if (isLoggedUserRepresentative()) {
			Student user = (Student) _loggedUser;
			try {
				user.setRepresentative(false);
				String results = user.getSurveyResults(disciplineName, projectName);
				return results;
			}
			finally {
				user.setRepresentative(true);
			}
		}
		else
			return _loggedUser.getSurveyResults(disciplineName, projectName);
	}

	public void createSurvey(String disciplineName, String projectName) 
		throws NoSuchDisciplineIdException,	NoSuchProjectIdException,
		CoreDuplicateSurveyException {

		((Student) _loggedUser).createSurvey(disciplineName, projectName);
	}

	public void cancelSurvey(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException, CoreNonEmptySurveyException,
		CoreSurveyFinishedException {

		((Student) _loggedUser).cancelSurvey(disciplineName, projectName);
	}

	public void openSurvey(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException, CoreOpeningSurveyException {

		((Student) _loggedUser).openSurvey(disciplineName, projectName);
	}

	public void closeSurvey(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException,	NoSuchProjectIdException,
		CoreNoSurveyException, CoreClosingSurveyException {

		((Student) _loggedUser).closeSurvey(disciplineName, projectName);
	}

	public void finishSurvey(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException, CoreFinishingSurveyException {

		((Student) _loggedUser).finishSurvey(disciplineName, projectName);
	}

	public Collection<String> getDisciplineSurveys(String disciplineName)
		throws NoSuchDisciplineIdException {
			
		return ((Student) _loggedUser).getDisciplineSurveys(disciplineName);
	}
}
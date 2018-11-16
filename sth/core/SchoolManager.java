package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

import java.util.Collection;

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
		int userId = getLoggedUser().getId();
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

	public void changePhoneNumber(Person person, String newPhoneNumber) {
		person.changePhoneNumber(newPhoneNumber);
	}
	
	public Collection<Person> getAllUsers() {
		return _school.getAllUsers();
	}

	public Collection<Person> getAllUsers(String str) {
		return _school.getAllUsers(str);
	}

	public Collection<Student> getStudents(String disciplineName) 
		throws NoSuchDisciplineIdException {
			
		return ((Teacher) getLoggedUser()).getStudents(disciplineName);
	}

	public void createNewProject(String disc, String proj)
		throws NoSuchDisciplineIdException {
		((Teacher) getLoggedUser()).getDiscipline(disc).addProject(proj);
	}

	public void closeProject(String disc, String proj)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException {
			((Teacher) getLoggedUser()).getDiscipline(disc).closeProject(proj);
		}
}
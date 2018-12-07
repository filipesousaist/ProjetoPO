package sth.core;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.other.MaxDisciplinesException;
import sth.core.exception.other.MaxRepresentativesException;

import sth.core.exception.survey.CoreDuplicateSurveyException;
import sth.core.exception.survey.CoreNoSurveyException;
import sth.core.exception.survey.CoreOpeningSurveyException;
import sth.core.exception.survey.CoreClosingSurveyException;
import sth.core.exception.survey.CoreFinishingSurveyException;
import sth.core.exception.survey.CoreNonEmptySurveyException;
import sth.core.exception.survey.CoreSurveyFinishedException;

public class Student extends Person {
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;
	
	private static final int MAX_DISCIPLINES = 6;
	
	private boolean _isRepresentative;

	private Course _course;
	private List<Discipline> _disciplines = new ArrayList<>();

	Student(int id, String name, String phoneNumber, boolean rep) {
		super(id, name, phoneNumber);
		_isRepresentative = rep;
	}

	boolean setRepresentative(boolean rep) {
		boolean result;
		try {
			if (rep)
				result = _course.addRepresentative(this);
			else
				result = _course.removeRepresentative(this);
			if (result)
				_isRepresentative = rep;
			return result;
		}
		catch (MaxRepresentativesException mre) {
			System.out.println(mre.getMessage());
			return false;
		}
	}

	boolean isRepresentative() {
		return _isRepresentative;
	}

	void addDiscipline(Discipline d) throws MaxDisciplinesException {
		if (_disciplines.size() >= MAX_DISCIPLINES)
			throw new MaxDisciplinesException(super.getName());
		if (! _disciplines.contains(d))
			_disciplines.add(d);
	}

	Course getCourse() {
		return _course;
	}

	String getPersonStr() {
		return _isRepresentative ? "DELEGADO" : "ALUNO";
	}

	PersonType getPersonType() {
		return PersonType.STUDENT;
	}

	private Discipline getDiscipline(String disciplineName)
		throws NoSuchDisciplineIdException {

		for (Discipline d: _disciplines)
			if (d.getName().equals(disciplineName))
				return d;
		throw new NoSuchDisciplineIdException(disciplineName);
	}

	void deliverProject(String disciplineName, String projectName, String message)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException {

		getDiscipline(disciplineName).deliverProject(this, projectName, message);
	}

	void createSurvey(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreDuplicateSurveyException {

		_course.getDiscipline(disciplineName).createSurvey(projectName);
	}

	void openSurvey(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException, CoreOpeningSurveyException {

		_course.getDiscipline(disciplineName).openSurvey(projectName);
	}

	void closeSurvey(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException, CoreClosingSurveyException {

		_course.getDiscipline(disciplineName).closeSurvey(projectName);
	}

	void finishSurvey(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException, CoreFinishingSurveyException {

		_course.getDiscipline(disciplineName).finishSurvey(projectName);
	}

	void cancelSurvey(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException, CoreNonEmptySurveyException,
		CoreSurveyFinishedException {

		_course.getDiscipline(disciplineName).cancelSurvey(projectName);
	}

	void answerSurvey(String disciplineName, 
		String projectName, int hours, String message) 
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException {

		getDiscipline(disciplineName).answerSurvey(
			this, projectName, hours, message);
	}

	Collection<String> getDisciplineSurveys(String disciplineName)
		throws NoSuchDisciplineIdException {

		return _course.getDiscipline(disciplineName).getSurveyResultsFor(this);
	}

	String getSurveyResults(String disciplineName, String projectName) 
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException {

		return getDiscipline(disciplineName).getSurveyResultsFor(
			this, projectName);
	}

	String getFinishedSurveyResults(Project p) {
		Survey s = p.getSurvey();

		if (_isRepresentative) {
			Discipline d = p.getDiscipline();
			return d.getName() + " - " + p.getName() + " - " +
				s.getNumberOfAnswers() + " respostas - " + 
				s.getAverageTime() + " horas";
		}
		else
			return " * Número de respostas: " + s.getNumberOfAnswers() + 
				"\n * Tempo médio (horas): " + s.getAverageTime();
	}

	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] = lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		if (_course == null) {
			_course = school.parseCourse(components[0]);
			_course.enrollStudent(this);
		}

		Discipline dis = _course.parseDiscipline(components[1]);

		dis.enrollStudent(this);
	}

	@Override
	public String toString() {
		String s = super.toString();

		Collections.sort(_disciplines);

		for (Discipline d: _disciplines)
			s += "\n* " + _course.getName() + " - " + d.getName();
		return s;
	}
}

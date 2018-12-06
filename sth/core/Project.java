package sth.core;

import java.io.Serializable;

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import sth.core.Student;
import sth.core.Discipline;
import sth.core.Survey;

import sth.core.exception.NoSuchProjectIdException;

import sth.core.exception.survey.CoreOpeningSurveyException;
import sth.core.exception.survey.CoreClosingSurveyException;
import sth.core.exception.survey.CoreFinishingSurveyException;
import sth.core.exception.survey.CoreSurveyFinishedException;
import sth.core.exception.survey.CoreNonEmptySurveyException;
import sth.core.exception.survey.CoreNoSurveyException;
import sth.core.exception.survey.CoreDuplicateSurveyException;


 public class Project implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;
	private String _description;
	private boolean _closed;

	private Discipline _discipline;
	private Survey _survey;

	private Set<Submission> _submissions = new HashSet<>();

	Project(Discipline discipline, String name) {
		_discipline = discipline;
		_name = name;
		_closed = false;
	}

	Project(Discipline discipline, String name, String descr) {
		this(discipline, name);
		_description = descr;
	}

	Discipline getDiscipline(){
		return _discipline;
	}

	String getName() {
		return _name;
	}

	private Survey getCreatedSurvey() throws CoreNoSurveyException {
		if (_survey == null)
			throw new CoreNoSurveyException();
		return _survey;
	}

	Survey getSurvey() throws CoreNoSurveyException {
		return _survey;
	}

	void close() {
		_closed = true;
		try {
			if (_survey != null)
				_survey.open();
		}
		catch (CoreOpeningSurveyException cose) {
			cose.printStackTrace();
		}
	}

	void addSubmission(Student student, String message) 
		throws NoSuchProjectIdException {

		if (_closed)
			throw new NoSuchProjectIdException(_name);

		Submission submission = new Submission(student, message);
		_submissions.remove(submission);
		_submissions.add(submission);
	}

	Collection<Submission> getSubmissions() {
		return Collections.unmodifiableSet(_submissions);
	}

	void createSurvey() 
		throws CoreDuplicateSurveyException, NoSuchProjectIdException {

		if (_closed)
			throw new NoSuchProjectIdException(_name);
		else if (_survey != null)
			throw new CoreDuplicateSurveyException();

		_survey = new Survey(this);
	}

	void cancelSurvey()
		throws CoreNoSurveyException, CoreNonEmptySurveyException,
		CoreSurveyFinishedException {

		getCreatedSurvey().cancel();
	}

	void openSurvey()
		throws CoreNoSurveyException, CoreOpeningSurveyException {

		if (! _closed)
			throw new CoreOpeningSurveyException();
		getCreatedSurvey().open();
	}

	void closeSurvey() 
		throws CoreNoSurveyException, CoreClosingSurveyException {

		if (! _closed)
			throw new CoreClosingSurveyException();
		getCreatedSurvey().close();
	}

	void finishSurvey()
		throws CoreNoSurveyException, CoreFinishingSurveyException {

		if (! _closed)
			throw new CoreFinishingSurveyException();
		getCreatedSurvey().finish();
	}

	void deleteSurvey() {
		_survey = null;
	}

	void answerSurvey(Student student, int hours, String message) 
		throws NoSuchProjectIdException, CoreNoSurveyException {

		boolean hasSubmitted = false;
		for (Submission s: _submissions) 
			if (s.getStudent().equals(student)) {
				hasSubmitted = true;
				break;
			}
		if (!hasSubmitted)
			throw new NoSuchProjectIdException(getName());
		_survey.addAnswer(student, hours, message);
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null &&
			obj instanceof Project &&
			_name.equals(((Project) obj)._name);
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}
}
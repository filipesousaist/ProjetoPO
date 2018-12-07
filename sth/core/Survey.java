package sth.core;

import java.io.Serializable;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

import sth.core.exception.survey.CoreOpeningSurveyException;
import sth.core.exception.survey.CoreClosingSurveyException;
import sth.core.exception.survey.CoreFinishingSurveyException;
import sth.core.exception.survey.CoreSurveyFinishedException;
import sth.core.exception.survey.CoreNonEmptySurveyException;
import sth.core.exception.survey.CoreNoSurveyException;

public class Survey extends Subject implements Serializable {

	private static final long serialVersionUID = 201810051538L;

	private final Project _project;
	private Set<Student> _students = new HashSet<>();
	private List<Answer> _answers = new ArrayList<>();

	private final State _created = new Created();
	private final State _open = new Open();
	private final State _closed = new Closed();
	private final State _finished = new Finished();

	private State _state = _created;

	Survey(Project project) {
		_project = project;

		Discipline d = _project.getDiscipline();
		List<Person> people = new ArrayList<>();
		people.addAll(d.getStudents());
		people.addAll(d.getTeachers());
		people.addAll(d.getCourse().getRepresentatives());
		
		for (Person p: people)
			attach(p);
	}

	void open() throws CoreOpeningSurveyException {
		_state.open();
	}

	void close() throws CoreClosingSurveyException {
		_state.close();
	}

	void finish() throws CoreFinishingSurveyException {
		_state.finish();
	}

	void cancel() throws CoreNonEmptySurveyException, 
		CoreSurveyFinishedException {

		_state.cancel();
	}

	void addAnswer(Student student, int hours, String message) 
		throws CoreNoSurveyException {
		_state.addAnswer(student, hours, message);
	}

	String getResultsFor(Person p) {
		return _state.getResultsFor(p);
	}

	Notification getNotification() {
		return _state.getNotification();
	}

	int getNumberOfAnswers() {
		return _students.size();
	}

	int getAverageTime() {
		int totalTime = 0;
		if (_answers.size() != 0) {
			for (Answer a: _answers) 
				totalTime += a.getHours();
			return (int) (totalTime / getNumberOfAnswers());
		}
		return totalTime;
	}

	int getMinimumTime() {
		int minTime = (_answers.size() != 0) ? _answers.get(0).getHours() : 0;
		for (Answer a: _answers){
			if (a.getHours() < minTime)
				minTime = a.getHours();
		}
		return minTime;
	}

	int getMaximumTime() {
		int maxTime = (_answers.size() != 0) ? _answers.get(0).getHours() : 0;
		for (Answer a: _answers){
			if (a.getHours() > maxTime)
				maxTime = a.getHours();
		}
		return maxTime;
	}

	private abstract class State implements Serializable {
		private static final long serialVersionUID = 201810051538L;

		abstract void open() throws CoreOpeningSurveyException;
		abstract void close() throws CoreClosingSurveyException;
		abstract void finish() throws CoreFinishingSurveyException;
		abstract void cancel() throws CoreNonEmptySurveyException,
			CoreSurveyFinishedException;

		void addAnswer(Student student, int hours, String message) 
			throws CoreNoSurveyException {

			if (!_students.contains(student)) {
				_students.add(student);
				_answers.add(new Answer(hours, message));
			}
		}

		String getResultsFor(Person p) {
			Discipline d = _project.getDiscipline();
			return d.getName() + " - " + _project.getName();
		}

		Notification getNotification() {
			return null;
		}

	}

	private class Created extends State {
		private static final long serialVersionUID = 201810051538L;

		void open() {
			_state = _open;
			notifyObservers();
		}

		void close() throws CoreClosingSurveyException {
			throw new CoreClosingSurveyException();
		}

		void finish() throws CoreFinishingSurveyException {
			throw new CoreFinishingSurveyException();
		}

		void cancel() {
			_project.deleteSurvey();
		}

		@Override
		void addAnswer(Student student, int hours, String message) 
			throws CoreNoSurveyException {

			throw new CoreNoSurveyException();
		}

		@Override 
		String getResultsFor(Person p) {
			return super.getResultsFor(p) + " (por abrir)";
		}
	}

	private class Open extends State {
		private static final long serialVersionUID = 201810051538L;

		void open() throws CoreOpeningSurveyException {
			throw new CoreOpeningSurveyException();
		}

		void close() {
			_state = _closed;
		}

		void finish() throws CoreFinishingSurveyException {
			throw new CoreFinishingSurveyException();
		}

		void cancel() throws CoreNonEmptySurveyException {
			if (! _answers.isEmpty())
				throw new CoreNonEmptySurveyException();
			_project.deleteSurvey();
		}

		@Override 
		String getResultsFor(Person p) {
			return super.getResultsFor(p) + " (aberto)";
		}

		@Override
		Notification getNotification() {
			Discipline d = _project.getDiscipline();

			return new Notification("Pode preencher inquérito do projecto " + 
				_project.getName() + " da disciplina " + d.getName());
		}
	}

	private class Closed extends State {
		private static final long serialVersionUID = 201810051538L;

		void open() {
			_state = _open;
			notifyObservers();
		}

		void close() {
			/* Do nothing */
		}

		void finish() {
			_state = _finished;
			notifyObservers();
		}

		void cancel() {
			_state = _open;
			notifyObservers();
		}

		@Override
		void addAnswer(Student student, int hours, String message) 
			throws CoreNoSurveyException {

			throw new CoreNoSurveyException();
		}

		@Override 
		String getResultsFor(Person p) {
			return super.getResultsFor(p) + " (fechado)";
		}
	}

	private class Finished extends State {
		private static final long serialVersionUID = 201810051538L;

		void open() throws CoreOpeningSurveyException {
			throw new CoreOpeningSurveyException();
		}

		void close() throws CoreClosingSurveyException {
			throw new CoreClosingSurveyException();
		}

		void finish() {
			/* Do nothing */
		}

		void cancel() throws CoreSurveyFinishedException {
			throw new CoreSurveyFinishedException();
		}

		@Override
		void addAnswer(Student student, int hours, String message)
			throws CoreNoSurveyException {

			throw new CoreNoSurveyException();
		}

		@Override 
		String getResultsFor(Person p) {
			return super.getResultsFor(p) + "\n" + 
				p.getFinishedSurveyResults(_project);
		}

		Notification getNotification() {
			Discipline d = _project.getDiscipline();
			return new Notification("Resultados do inquérito do projecto " + 
				_project.getName() + " da disciplina " + d.getName());
		}
	}
}
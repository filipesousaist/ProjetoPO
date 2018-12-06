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
		_state.addAnswer();
	}

	Notification getNotification() {
		_state.getNotification();
	}

	private abstract class State {
		void open() throws CoreOpeningSurveyException {
			_state = open;
		}

		void close() throws CoreClosingSurveyException {
			_state = closed;
		}
		void finish() throws CoreFinishingSurveyException {
			_state = finished;
		}
		void cancel() throws CoreNonEmptySurveyException,
			CoreSurveyFinishedException {

			_project.deleteSurvey();
		}
		void addAnswer(Student student, int hours, String message) 
			throws CoreNoSurveyException {

			if (! _students.contains(student)) {
				_students.add(student);
				_answers.add(new Answer(hours, message));
			}
		}
	}

	private class Created extends State {
		@Override
		void close() throws CoreClosingSurveyException {
			throw new CoreClosingSurveyException();
		}

		@Override
		void finish() throws CoreFinishingSurveyException {
			throw new CoreFinishingSurveyException();
		}

		@Override
		void addAnswer(Student student, int hours, String message) {
			throw new CoreNoSurveyException();
		}
		
	}

	private class Open extends State {
		@Override
		void open() throws CoreOpeningSurveyException {
			throw new CoreOpeningSurveyException();
		}

		@Override
		void finish() throws CoreFinishingSurveyException {
			throw new CoreFinishingSurveyException();
		}

		@Override
		void cancel() throws CoreNonEmptySurveyException {
			if (! _answers.isEmpty())
				throw new CoreNonEmptySurveyException();
			super.cancel();
		}

		Notification getNotification() {
			Discipline d = _project.getDiscipline();

			return new Notification("Pode preencher inquérito do projeto " + 
				_project.getName() + " da disciplina " + d.getName());
		}
	}

	private class Closed extends State {
		@Override
		void open() {
			super.open();
			notifyObserver();
		}

		@Override
		void finish() {
			super.finish();
			notifyObserver();
		}

		@Override
		void cancel() {
			super.open();
			notifyObserver();
		}

		@Override
		void addAnswer(Student student, int hours, String message) {
			throw new CoreNoSurveyException();
		}
	}

	private class Finished extends State {
		@Override
		void open() throws CoreOpeningSurveyException {
			throw new CoreOpeningSurveyException();
		}

		@Override
		void close() throws CoreClosingSurveyException {
			throw new CoreClosingSurveyException();
		}
		
		@Override
		void cancel() throws CoreSurveyFinishedException {
			throw new CoreSurveyFinishedException();
		}

		@Override
		void addAnswer(Student student, int hours, String message) {
			throw new CoreNoSurveyException();
		}

		Notification getNotification() {
			Discipline d = _project.getDiscipline();
			return new Notification("Resultados do inquérito do projeto " + 
				_project.getName() + " da disciplina " + d.getName());
		}
	}
}
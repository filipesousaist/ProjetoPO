package sth.core;

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

public class Survey extends Subject {
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

	Project getProject() {
		return _project;
	}

	Notification getNotification() {
		_state.getNotification();
	}

	void addAnswer(Student student, int hours, String message) {
		
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

	void cancel() throws CoreNonEmptySurveyException, CoreNoSurveyException,
		CoreSurveyFinishedException {

		_state.cancel();
	}

	private abstract class State {
		abstract void open() throws CoreOpeningSurveyException;
		abstract void close() throws CoreClosingSurveyException;
		abstract void finish() throws CoreFinishingSurveyException;
		abstract void cancel() throws CoreNonEmptySurveyException,
			CoreSurveyFinishedException;
		abstract void addAnswer(Student student, int hours, String message) throws
			CoreNoSurveyException;
		abstract Notification getNotification();
	}

	private class Created extends State {
		void open() throws CoreOpeningSurveyException {
			if (_project.isOpen())
				throw new CoreOpeningSurveyException();
			_state = _open;
			notifyObserver();
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

		void addAnswer(Student student, int hours, String message);
		
	}

	private class Open extends State {
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

		Notification getNotification() {
			Discipline d = _project.getDiscipline();

			return new Notification("Pode preencher inquérito do projeto " + 
				_project.getName() + " da disciplina " + d.getName());
		}
	}

	private class Closed extends State {
		void open() {
			_state = _open;
			notifyObserver();
		}

		void close() {
			/* Do nothing */
		}

		void finish() {
			_state = _finished;
			notifyObserver();
		}

		void cancel() {
			_state = _open;
			notifyObserver();
		}
	}

	private class Finished extends State {
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

		Notification getNotification() {
			Discipline d = _project.getDiscipline();
			return new Notification("Resultados do inquérito do projeto " + 
				_project.getName() + " da disciplina " + d.getName());
		}
	}
}
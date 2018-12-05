package sth.core.project;

import sth.core.Student;

import java.util.Set;
import java.util.HashSet;

import sth.core.exception.survey.CoreOpeningSurveyException;
import sth.core.exception.survey.CoreClosingSurveyException;
import sth.core.exception.survey.CoreFinishingSurveyException;
import sth.core.exception.survey.CoreSurveyFinishedException;
import sth.core.exception.survey.CoreNonEmptySurveyException;

public class Survey {
	private final Project _project;
	private Set<Student> _students = new HashSet<>();
	private List<Answer> _answers = new ArrayList<>();

	private final State _created = new Created();
	private final State _open = new Open();
	private final State _closed = new Closed();
	private final State _finished = new Finished();

	private SurveyState _state = _created;

	Survey(Project project) {
		_project = project;
	}

	Project getProject() {
		return _project;
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

	private interface State {
		void open() throws CoreOpeningSurveyException;
		void close() throws CoreClosingSurveyException;
		void finish() throws CoreFinishingSurveyException;
		void cancel() throws CoreNonEmptySurveyException,
			CoreSurveyFinishedException;
		void addAnswer(Student student, int hours, String message) throws
			CoreNoSurveyException;
	}

	private class Created implements State {
		void open() throws CoreOpeningSurveyException {
			if (_project.isOpen())
				throw new CoreOpeningSurveyException();
			_state = _open;
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

		void addAnswer(Student student, int hours, String message)
		
	}

	private class Open implements State {
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
	}

	private class Closed implements State {
		void open() {
			_state = _open;
		}

		void close() {
			/* Do nothing */
		}

		void finish() {
			_state = _finished;
		}

		void cancel() {
			_state = _open;
		}
	}

	private class Finished implements State {
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
	}
}
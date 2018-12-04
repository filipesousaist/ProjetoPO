package sth.core.project;

import sth.core.Student;

import java.util.Set;
import java.util.HashSet;

public class Survey{

	private Set<Student> _students = new HashSet<>();
	private List<Answer> _answers = new ArrayList<>();

	private SurveyState _surveyState = new CreatedSurvey();
	private Project _project;

	Survey(Project project) {
		_project = project;
	}

	Project getProject() {
		return _project;
	}

	void addAnswer(Student student, int hours, String message) {
		
	}

	void open() {
		_surveyState = _surveyState.open();
	}

	void close() {
		_surveyState = _surveyState.close();
	}

	void finish() {
		_ surveyState = _surveyState.finish();
	}

	boolean isEmpty() {
		return _answers.isEmpty(); 
	}

}
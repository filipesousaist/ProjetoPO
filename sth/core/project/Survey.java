package sth.core.project;

import sth.core.Student;

import java.util.Set;
import java.util.HashSet;

public class Survey{

	private Set<Student> _students = new HashSet<>();
	private List<Answer> _answers = new ArrayList<>();

	SurveyState _surveyState = new CreatedSurvey();

	public void open(){
		_surveyState.open();
	}

	public void close() {
		_surveyState.close();
	}

	public void finalize() {
		_surveyState.finalize();
	}

	public void setState(){
		return _surveyState;
	}

}
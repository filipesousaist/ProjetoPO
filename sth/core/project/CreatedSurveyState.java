package sth.core.project;

public class CreatedSurveyState extends SurveyState {

	boolean canAddAnswer() {
		return false;
	}

	SurveyState open() {
		return new OpenSurvey();
	}
}
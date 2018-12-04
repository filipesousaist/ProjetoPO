package sth.core.project;

public class OpenSurveyState extends SurveyState {

	boolean canAddAnswer(){
		return true;
	}

	SurveyState close() {
		return new ClosedSurveyState();
	}
}
package sth.core.project;

public class ClosedSurveyState extends SurveyState{

	boolean canAddAnswer() {
		return false;
	}

	SurveyState open() {
		return new OpenSurveyState();
	}

	SurveyState close() {
		return this;
	}

	SurveyState finish() {
		return new FinishedSurveyState();
	}

	SurveyState cancel() {
		return new OpenSurveyState();
	}
}
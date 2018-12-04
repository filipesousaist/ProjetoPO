package sth.core.project;

public class CreatedSurvey extends SurveyState{
	void addAnswer(Student student, int hours, String answer){

	}
	String getResultsFor(Person p){

	}

	SurveyState open(){
		return new OpenSurvey();
	}

	SurveyState close(){

	}

	SurveyState cancel(){

	}

	SurveyState finalize(){

	}
}
package sth.core.project;

public abstract class SurveyState{
	void addAnswer(Student student, int hours, String answer);
	String getResultsFor(Person p);
	SurveyState open(){
		
	}
	SurveyState close(){

	}
	SurveyState cancel(){

	}
	SurveyState finalize(){
		
	}
}
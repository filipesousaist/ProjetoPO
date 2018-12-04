package sth.core;

public interface SurveyState{
	void created();
	void open();
	void closed();
	void finalized();
}
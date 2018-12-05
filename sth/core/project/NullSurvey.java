package sth.core.project;

import sth.core.exception.survey.CoreNoSurveyException;

public class NullSurvey extends Survey {
	void open() throws CoreNoSurveyException {
		throw new CoreNoSurveyException();
	}
	void close() throws CoreNoSurveyException {
		throw new CoreNoSurveyException();
	}
	void finish() throws CoreNoSurveyException {
		throw new CoreNoSurveyException();
	}
	void cancel() throws CoreNoSurveyException {
		throw new CoreNoSurveyException();
	}
}
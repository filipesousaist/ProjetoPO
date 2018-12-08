package sth.core;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.survey.CoreNoSurveyException;

interface CanGetSurveyResults {
	String getSurveyResults(String disciplineName, String projectName) 
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException;

	String getFinishedSurveyResults(Project p);
}
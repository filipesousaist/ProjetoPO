package sth.core.project;

import sth.core.exception.survey.CoreClosingSurveyException;
import sth.core.exception.survey.CoreOpeningSurveyException;
import sth.core.exception.survey.CoreFinishingSurveyException;
import sth.core.exception.survey.CoreNoSurveyException;
import sth.core.exception.survey.CoreNonEmptySurveyException;
import sth.core.exception.survey.CoreSurveyFinishedException;

public abstract class SurveyState{

	private Survey _survey;

	SurveyState(Survey survey){
		_survey = survey;
	}

	void addAnswer() {
		
	}


	SurveyState open() throws CoreOpeningSurveyException {
		Project project = _survey.getProject();
		Discipline discipline = project.getDiscipline();
		throw new CoreOpeningSurveyException(discipline.getName(),
			project.getName());
	}

	SurveyState close() throws CoreClosingSurveyException {
		Project project = _survey.getProject();
		Discipline discipline = project.getDiscipline();
		throw new CoreClosingSurveyException(discipline.getName(),
			project.getName());
	}

	SurveyState cancel() throws CoreNoSurveyException, 
		CoreNonEmptySurveyException, CoreSurveyFinishedException {

		_survey.getProject().removeSurvey();
	}

	SurveyState finish() throws CoreFinishingSurveyException {
		Project project = _survey.getProject();
		Discipline discipline = project.getDiscipline();
		throw new CoreFinishingSurveyException(discipline.getName(),
			project.getName());
	}

	Survey getSurvey() {
		return _survey;
	}
}
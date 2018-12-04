package sth.core.project;

import sth.core.exception.survey.CoreSurveyFinishedException;

public class FinishedSurveyState extends SurveyState {

	void addAnswer() {
		
	}

	SurveyState finish() {
		return this;
	}

	SurveyState cancel() throws CoreSurveyFisnishedException {
		Project project = super.getSurvey().getProject();
		Discipline discipline = project.getDiscipline();
		throw new CoreSurveyFinishedException(discipline.getName(),
			project.getName());
	}
}


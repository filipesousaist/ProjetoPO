package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.core.exception.survey.CoreNoSurveyException;
import sth.core.exception.survey.CoreFinishingSurveyException;

import sth.app.exception.NoSurveyException;
import sth.app.exception.FinishingSurveyException;

/**
 * 4.6.5. Finish survey.
 */
public class DoFinishSurvey extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoFinishSurvey(SchoolManager receiver) {
		super(Label.FINISH_SURVEY, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */ 
	@Override
	public final void myExecute() throws DialogException,
		NoSuchDisciplineIdException, NoSuchProjectIdException {

		String disciplineName = _discipline.value();
		String projectName = _project.value();

		try {
			_receiver.finishSurvey(disciplineName, projectName);
		}
		catch (CoreNoSurveyException cnse) {
			throw new NoSurveyException(disciplineName, projectName);
		}
		catch (CoreFinishingSurveyException cfse) {
			throw new FinishingSurveyException(disciplineName, projectName);
		}
	}
	
}

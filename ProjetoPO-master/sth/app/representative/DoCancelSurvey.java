package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

import sth.core.exception.survey.CoreNoSurveyException;
import sth.core.exception.survey.CoreNonEmptySurveyException;
import sth.core.exception.survey.CoreSurveyFinishedException;

import sth.app.exception.NoSurveyException;
import sth.app.exception.NonEmptySurveyException;
import sth.app.exception.SurveyFinishedException;

/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoCancelSurvey(SchoolManager receiver) {
		super(Label.CANCEL_SURVEY, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws NoSuchProjectIdException, 
		NoSuchDisciplineIdException, DialogException {

		String disciplineName = _discipline.value();
		String projectName = _project.value();

		try {
			_receiver.cancelSurvey(disciplineName, projectName);
		}
		catch (CoreNoSurveyException cnse) {
			throw new NoSurveyException(disciplineName, projectName);
		}
		catch (CoreNonEmptySurveyException cnese) {
			throw new NonEmptySurveyException(disciplineName, projectName);
		}
		catch (CoreSurveyFinishedException csfe) {
			throw new SurveyFinishedException(disciplineName, projectName);
		}
	}

}

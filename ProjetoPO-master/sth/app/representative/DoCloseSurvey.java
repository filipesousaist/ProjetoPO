package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

import sth.core.exception.survey.CoreNoSurveyException;
import sth.core.exception.survey.CoreClosingSurveyException;

import sth.app.exception.NoSurveyException;
import sth.app.exception.ClosingSurveyException;

/**
 * 4.5.4. Close survey.
 */
public class DoCloseSurvey extends sth.app.common.ProjectCommand {
	/**
	 * @param receiver
	 */
	public DoCloseSurvey(SchoolManager receiver) {
		super(Label.CLOSE_SURVEY, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws NoSuchProjectIdException,
		NoSuchDisciplineIdException, DialogException {

		String disciplineName = _discipline.value();
		String projectName = _project.value();

		try {
			_receiver.closeSurvey(disciplineName, projectName);
		}
		catch (CoreNoSurveyException cnse) {
			throw new NoSurveyException(disciplineName, projectName);
		}
		catch (CoreClosingSurveyException ccse) {
			throw new ClosingSurveyException(disciplineName, projectName);
		}
	}
}
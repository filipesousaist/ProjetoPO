package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.core.exception.survey.CoreNoSurveyException;
import sth.core.exception.survey.CoreOpeningSurveyException;

import sth.app.exception.NoSurveyException;
import sth.app.exception.OpeningSurveyException;

/**
 * 4.6.3. Open survey.
 */
public class DoOpenSurvey extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoOpenSurvey(SchoolManager receiver) {
		super(Label.OPEN_SURVEY, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */ 
	@Override
	public final void myExecute() throws DialogException, 
		NoSuchDisciplineIdException, NoSuchProjectIdException {

		String disciplineName = _discipline.value();
		String projectName = _project.value();

		try {
			_receiver.openSurvey(disciplineName, projectName);
		}
		catch (CoreNoSurveyException cnse) {
			throw new NoSurveyException(disciplineName, projectName);
		}
		catch (CoreOpeningSurveyException cose) {
			throw new OpeningSurveyException(disciplineName, projectName);
		}
	}

}

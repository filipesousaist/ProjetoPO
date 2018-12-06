package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.core.exception.survey.CoreNoSurveyException;

import sth.app.exception.NoSurveyException;

/**
 * 4.4.5. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoShowSurveyResults(SchoolManager receiver) {
		super(Label.SHOW_SURVEY_RESULTS, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws DialogException, 
		NoSuchDisciplineIdException, NoSuchProjectIdException {
		
		String disciplineName = _discipline.value();
		String projectName = _project.value();

		try {
			String results = _receiver.getSurveyResults(disciplineName, projectName);
			_display.addLine(results);
			_display.display();
		}
		catch (CoreNoSurveyException cnse) {
			throw new NoSurveyException(disciplineName, projectName);
		}
	}
}

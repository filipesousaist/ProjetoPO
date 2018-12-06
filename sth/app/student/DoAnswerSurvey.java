package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.core.exception.survey.CoreNoSurveyException;

import sth.app.exception.NoSurveyException;

/**
 * 4.5.2. Answer survey.
 */
public class DoAnswerSurvey extends sth.app.common.ProjectCommand {
	Input<Integer> _hoursInput;
	Input<String> _commentInput;

	/**
	 * @param receiver
	 */
	public DoAnswerSurvey(SchoolManager receiver) {
		super(Label.ANSWER_SURVEY, receiver);
		_hoursInput = _form.addIntegerInput(Message.RequestProjectHours());
		_commentInput = _form.addStringInput(Message.RequestComment());
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
		String disciplineName = _discipline.value();
		String projectName = _project.value();
		int hours = _hoursInput.value();
		String comment = _commentInput.value();

		try {
			_receiver.answerSurvey(disciplineName, projectName, hours, comment);
		}
		catch (CoreNoSurveyException cnse) {
			throw new NoSurveyException(disciplineName, projectName);
		}

	}

}

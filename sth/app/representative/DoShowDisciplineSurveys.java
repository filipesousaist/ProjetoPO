package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;
import sth.core.Survey;

import sth.core.exception.NoSuchDisciplineIdException;

import sth.app.exception.NoSuchDisciplineException;

/**
 * 4.6.6. Show discipline surveys.
 */
public class DoShowDisciplineSurveys extends Command<SchoolManager> {

	private Input<String> _disciplineNameInput;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineSurveys(SchoolManager receiver) {
		super(Label.SHOW_DISCIPLINE_SURVEYS, receiver);
		_disciplineNameInput = 
			_form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		_form.parse();
		String disciplineName = _disciplineNameInput.value();

		try {
			List<Survey> surveys =
				new ArrayList<>(_receiver.getDisciplineSurveys(disciplineName));
		}
		catch (NoSuchDisciplineIdException nsdie) {
			throw new NoSuchDisciplineException(disciplineName);
		}

		for (Survey s: surveys){
			_display.addLine(s.toString());
		}
		_display.display()
	}
}

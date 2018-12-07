package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
 * 4.2.2. Change phone number.
 */
public class DoChangePhoneNumber extends Command<SchoolManager> {

	private Input<String> _newPhoneNumberInput;

	/**
	 * @param receiver
	 */
	public DoChangePhoneNumber(SchoolManager receiver) {
		super(Label.CHANGE_PHONE_NUMBER, receiver);
		_newPhoneNumberInput = _form.addStringInput(Message.requestPhoneNumber());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		_form.parse();

		String newPhoneNumber = _newPhoneNumberInput.value();
		_receiver.changePhoneNumber(newPhoneNumber);
		
		_display.add(_receiver.getLoggedUser().toString());
		_display.display();
	}
}

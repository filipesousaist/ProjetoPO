package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

//FIXME import other classes if needed

/**
 * 4.2.2. Change phone number.
 */
public class DoChangePhoneNumber extends Command<SchoolManager> {

	//FIXME add input fields if needed
	private Input<String> _newPhoneNumberInput;

	/**
	 * @param receiver
	 */
	public DoChangePhoneNumber(SchoolManager receiver) {
		super(Label.CHANGE_PHONE_NUMBER, receiver);
		//FIXME initialize input fields if needed
		_newPhoneNumberInput = _form.addStringInput(Message.requestPhoneNumber());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		//FIXME implement command
		_form.parse();
		String newPhoneNumber = _newPhoneNumberInput.value();
		_receiver.changePhoneNumber(_receiver.getLoggedUser(), newPhoneNumber);
		_display.add(_receiver.getLoggedUser().toString());
		_display.display();
	}

}

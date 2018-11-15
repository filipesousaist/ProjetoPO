package sth.app.main;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;
import sth.core.School;
import sth.core.exception.NoSuchPersonIdException;

import sth.app.exception.NoSuchPersonException;

//FIXME import other classes if needed

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {

	//FIXME add input fields if needed
	private Input<String> _serialFilenameInput;
	/**
	 * @param receiver
	 */
	public DoOpen(SchoolManager receiver) {
		super(Label.OPEN, receiver);
		//FIXME initialize input fields if needed
		_serialFilenameInput = _form.addStringInput(Message.openFile());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, NoSuchPersonException {
		School oldSchool = _receiver.getSchool();
		try {
			_form.parse();
			String serialFilename = _serialFilenameInput.value();
			ObjectInputStream objInp = new ObjectInputStream(
				new FileInputStream(serialFilename));
			
			School newSchool = (School) objInp.readObject();
			objInp.close();

			int userID = _receiver.getLoggedUserId();
			_receiver.setSchool(newSchool);
			_receiver.login(userID);

		} 
		catch (FileNotFoundException fnfe) {
			_display.popup(Message.fileNotFound());
		} 
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		catch (NoSuchPersonIdException nspie) {
			_receiver.setSchool(oldSchool);
			throw new NoSuchPersonException(nspie.getId());
		}

	}

}

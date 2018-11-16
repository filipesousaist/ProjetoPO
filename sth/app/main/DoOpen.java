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

/**
 * 4.1.1. Open existing document.
 */
public class DoOpen extends Command<SchoolManager> {
	private Input<String> _serialFilenameInput;

	/**
	 * @param receiver
	 */
	public DoOpen(SchoolManager receiver) {
		super(Label.OPEN, receiver);
		_serialFilenameInput = _form.addStringInput(Message.openFile());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException, NoSuchPersonException {
		_form.parse();
		String serialFilename = _serialFilenameInput.value();
		try (ObjectInputStream objInput = new ObjectInputStream(
			new FileInputStream(serialFilename))) {
			
			_receiver.updateSchool((School) objInput.readObject());
		} 
		catch (FileNotFoundException fnfe) {
			_display.popup(Message.fileNotFound());
		} 
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		catch (NoSuchPersonIdException nspie) {
			throw new NoSuchPersonException(nspie.getId());
		}
	}
}

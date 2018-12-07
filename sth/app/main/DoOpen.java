package sth.app.main;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;
import sth.core.School;
import sth.core.Notification;

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
			_receiver.setSerialFilename(serialFilename);

			List<Notification> notifications = _receiver.getNotifications();
			for (Notification n: notifications)
				_display.addLine(n.getMessage());
			_display.display();
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

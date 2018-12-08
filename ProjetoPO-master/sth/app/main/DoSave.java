package sth.app.main;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
	private Input<String> _serialFilenameInput;
	/**
	 * @param receiver
	 */
	public DoSave(SchoolManager receiver) {
		super(Label.SAVE, receiver);
		_serialFilenameInput = _form.addStringInput(Message.newSaveAs());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		if (_receiver.getSerialFilename() == null) {
			_form.parse();
			_receiver.setSerialFilename(_serialFilenameInput.value());
		}
		try (ObjectOutputStream objOutput = new ObjectOutputStream(
			new FileOutputStream(_receiver.getSerialFilename()))) {
			
			objOutput.writeObject(_receiver.getSchool());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

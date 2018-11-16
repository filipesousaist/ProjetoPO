package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.4.1. Create project.
 */
public class DoCreateProject extends sth.app.common.ProjectCommand {

	/**
	 * @param receiver
	 */
	public DoCreateProject(SchoolManager receiver) {
		super(Label.CREATE_PROJECT, receiver);
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
		String disciplineName = _discipline.value();
		String projectName = _project.value();

		_receiver.createNewProject(disciplineName, projectName);
	}
}
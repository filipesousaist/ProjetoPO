package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;
import sth.core.Project;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.4.3. Show project submissions.
 */
public class DoShowProjectSubmissions extends sth.app.common.ProjectCommand {
	/**
	 * @param receiver
	 */
	public DoShowProjectSubmissions(SchoolManager receiver) {
		super(Label.SHOW_PROJECT_SUBMISSIONS, receiver);
	}

	/** @see sth.app.common.ProjectCommand#myExecute() */
	@Override
	public final void myExecute() throws DialogException, 
		NoSuchDisciplineIdException, NoSuchProjectIdException {
		
		String disciplineName = _discipline.value();
		String projectName = _project.value();

		Project project = _receiver.getProject(disciplineName, projectName);
		_display.addLine(project.toString());
		_display.display();
	}

}

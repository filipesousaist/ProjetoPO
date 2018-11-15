package sth.app.teaching;

import java.util.Collection;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.Discipline;
import sth.core.Student;
import sth.core.Teacher;

//FIXME import other classes if needed

/**
 * 4.4.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

	//FIXME add input fields if needed
	private Input<String> _disciplineToSearch;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineStudents(SchoolManager receiver) {
		super(Label.SHOW_COURSE_STUDENTS, receiver);
		//FIXME initialize input fields if needed
		_disciplineToSearch = _form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() throws DialogException {
		//FIXME implement command
		_form.parse();
		String discName = _disciplineToSearch.value();

		for(Student st: _receiver.getStudentsDisc(discName))
			_display.add(st.toString());
		_display.display();
	}
}

package sth.app.teaching;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;
import sth.core.Student;

import sth.app.exception.NoSuchDisciplineException;

import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.4.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {
	private Input<String> _disciplineInput;

	/**
	 * @param receiver
	 */
	public DoShowDisciplineStudents(SchoolManager receiver) {
		super(Label.SHOW_COURSE_STUDENTS, receiver);
		_disciplineInput = _form.addStringInput(Message.requestDisciplineName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() 
		throws DialogException, NoSuchDisciplineException {

		try {
			_form.parse();
			String discName = _disciplineInput.value();
			List<Student> students = 
				new ArrayList<>(_receiver.getStudents(discName));

			Collections.sort(students);
			for (Student s: students)
				_display.add(s.toString());
			_display.display();
		}
		catch (NoSuchDisciplineIdException nsdie) {
			throw new NoSuchDisciplineException(nsdie.getId());
		}
	}
}

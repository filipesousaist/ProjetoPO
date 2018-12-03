package sth.app.person;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;

import sth.core.SchoolManager;
import sth.core.Person;

/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {
	private Input<String> _strInput;
	
	/**
	 * @param receiver
	 */
	public DoSearchPerson(SchoolManager receiver) {
		super(Label.SEARCH_PERSON, receiver);
		_strInput = _form.addStringInput(Message.requestPersonName());
	}

	/** @see pt.tecnico.po.ui.Command#execute() */
	@Override
	public final void execute() {
		_form.parse();
		String str = _strInput.value();
		
		List<Person> matchingUsers = new ArrayList<Person>(_receiver.getAllUsers(str));
		Collections.sort(matchingUsers, 
			new Comparator<Person>() {
				public int compare(Person p1, Person p2) {
					return p1.getName().compareTo(p2.getName());
				}
			}
		);

		for (Person p: matchingUsers)
			_display.add(p.toString());
		_display.display();
	}
}

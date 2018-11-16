package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;

import sth.core.SchoolManager;
import sth.core.Person;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

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
    
    List<Person> matchingUsers = new ArrayList<>(_receiver.getAllUsers(str));
    Collections.sort(matchingUsers);

    for (Person p: matchingUsers)
      _display.add(p.toString());
    _display.display();
  }
}

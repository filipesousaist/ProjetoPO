package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;

import sth.core.SchoolManager;
import sth.core.Person;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * 4.2.3. Show all persons.
 */
public class DoShowAllPersons extends Command<SchoolManager> {
  /**
   * @param receiver
   */
  public DoShowAllPersons(SchoolManager receiver) {
    super(Label.SHOW_ALL_PERSONS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    List<Person> people = new ArrayList<>(_receiver.getAllUsers());
    Collections.sort(people);

    for (Person p: people)
      _display.addLine(p.toString());

    _display.display();
  }
}

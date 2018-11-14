package sth.core;

//FIXME import other classes if needed

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;
import java.io.Serializable;

/**
 * School implementation.
 */
public class School implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  //FIXME define object fields (attributes and, possibly, associations)
  private static final int START_ID = 100000;
  
  private String _name;
  private int _nextPersonId;
  
  private Set<Person> _people;
  private Set<Course> _courses;


  //FIXME implement constructors if needed
  School(String name) {
    _name = name;
    _nextPersonId = START_ID;
    _people = new HashSet<>();
  }
  
  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException {
    Parser p = new Parser(this);
    p.parseFile(filename);
  }
  
  Course parseCourse(String courseName) {
    for (Course c: _courses)
      if (c.getName().equals(courseName))
        return c;

    Course newCourse = new Course(courseName);
    addCourse(newCourse);
    return newCourse;
  }
  //FIXME implement other methods

  void addCourse(Course c) {
    _courses.add(c);
  }

  Person getPerson(int id) throws NoSuchPersonIdException {
    if (_people.contains(person)) /* contains() is O(1) in a HashSet */
      for (Person p: _people)
        if (p.getId() == id)
          return p;
    throw new NoSuchPersonIdException(id);
  }

  List<Person> getAllUsers(String str) {
	  List<Person> peopleList = new ArrayList<>(_people);
    Iterator<Person> it = peopleList.iterator();
	  while (it.hasNext()) {
      Person p = it.next();
		  if (! p.getName().contains(str))
			  it.remove();
	  }
    Collections.sort(peopleList, 
      new Comparator<Person>() {
        public int compare(Person p1, Person p2) {
          return p1.getId() - p2.getId();
        }
      } 
    );
	  return peopleList;
  }

}

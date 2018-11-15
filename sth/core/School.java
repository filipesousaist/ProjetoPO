package sth.core;

//FIXME import other classes if needed

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Comparator;

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
	
	private Map<Integer, Person> _people = new HashMap<>();
	private Set<Course> _courses = new HashSet<>();


	//FIXME implement constructors if needed
	School(String name) {
		_name = name;
		_nextPersonId = START_ID;
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

	void addPerson(Person p) {
		_people.put(p.getId(), p);
	}

	boolean idExists(int id) {
		return _people.containsKey(id);
	}

	Person getPerson(int id) throws NoSuchPersonIdException {
		if (idExists(id))
			return _people.get(id);
		throw new NoSuchPersonIdException(id);
	}

	Collection<Person> getAllUsers() {
		return _people.values();
	}

	Collection<Person> getAllUsers(String str) {
		Collection<Person> peopleList = _people.values();
		Iterator<Person> it = peopleList.iterator();
		while (it.hasNext()) {
			Person p = it.next();
			if (! p.getName().contains(str))
				it.remove();
		}
		return peopleList;
	}
}

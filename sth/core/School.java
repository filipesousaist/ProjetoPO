package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import java.io.IOException;
import java.io.Serializable;

/**
 * School implementation.
 *
 * @author  Grupo 68
 * @version 1.0
 */
public class School implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	/** Starting id for the school's people. */
	private static final int START_ID = 100000;
	
	/** School name. */
	private String _name;
	/** Id of the next person to be added to the school. */
	private int _nextPersonId;
	/** Collection of all the people in the school. */
	private Map<Integer, Person> _people = new HashMap<>();
	/** Collection of all courses of the school. */
	private Set<Course> _courses = new HashSet<>();

	/** School constructor. */
	School(String name) {
		_name = name;
		_nextPersonId = START_ID;
	}
	
	/** Imports the file with the given name. 
	 * @param filename Name of the file to import
	 * @throws BadEntryException if file has formatting errors 
	 * @throws IOException if an input/output exception occurs
	 */
	void importFile(String filename) throws IOException, BadEntryException {
		Parser p = new Parser(this);
		p.parseFile(filename);
	}
	
	/** Checks if a course with a given name exists in the school and 
	 * creates one if it doesn't.
	 * @param courseName Name of the course to parse
	 * @return course with the given name
	 */
	Course parseCourse(String courseName) {
		for (Course c: _courses)
			if (c.getName().equals(courseName))
				return c;

		Course newCourse = new Course(courseName);
		addCourse(newCourse);
		return newCourse;
	}

	/** Adds a course to the school.
	 * @param c Course to add
	 */
	void addCourse(Course c) {
		_courses.add(c);
	}

	/** Adds a person to the school.
	 * @param p Person to add
	 */
	void addPerson(Person p) {
		_people.put(p.getId(), p);
	}

	/** Adds an employee to the school.
	 * @param name Name of the employee
	 * @param phoneNumber Phone number of the employee
	 */
	void addEmployee(String name, String phoneNumber) {
		while (idExists(_nextPersonId))
			_nextPersonId ++;
		addPerson(new Employee(_nextPersonId, name, phoneNumber));
		_nextPersonId ++;
	}

	/** Adds a teacher to the school.
	 * @param name Name of the teacher
	 * @param phoneNumber Phone number of the teacher
	 */
	void addTeacher(String name, String phoneNumber) {
		while (idExists(_nextPersonId))
			_nextPersonId ++;
		addPerson(new Teacher(_nextPersonId, name, phoneNumber));
		_nextPersonId ++;
	}

	/** Adds a student to the school.
	 * @param name Name of the student
	 * @param phoneNumber Phone number of the student
	 */
	void addStudent(String name, String phoneNumber) {
		while (idExists(_nextPersonId))
			_nextPersonId ++;
		addPerson(new Student(_nextPersonId, name, phoneNumber, false));
		_nextPersonId ++;
	}

	/** Adds a representative to the school.
	 * @param name Name of the representative
	 * @param phoneNumber Phone number of the representative
	 */
	void addRepresentative(String name, String phoneNumber) {
		while (idExists(_nextPersonId))
			_nextPersonId ++;
		addPerson(new Student(_nextPersonId, name, phoneNumber, true));
		_nextPersonId ++;
	}

	/** Checks if a person with a given id exists in the school.
	 * @param id Id of the person to search
	 * @return true when a person with the given id exists in the school
	 */
	boolean idExists(int id) {
		return _people.containsKey(id);
	}

	/** Gets the person with a given id.
	 * @param id Id of the person
	 * @throws NoSuchPersonIdException if no person with the given id 
	 * exists in the school
	 * @return the person in the school with the given id
	 */
	Person getPerson(int id) throws NoSuchPersonIdException {
		if (idExists(id))
			return _people.get(id);
		throw new NoSuchPersonIdException(id);
	}

	/** Gets all people registered in the school.
	 * @return all people registered in the school
	 */
	Collection<Person> getAllUsers() {
		return Collections.unmodifiableCollection(_people.values());
	}

	/** Gets all people whose name contains a given string.
	 * @param str String to search in people's name
	 * @return a collection of all people in school 
	 * whose name cointains the given string
	 */
	Collection<Person> getAllUsers(String str) {
		Collection<Person> peopleList = new HashSet<>(_people.values());
		Iterator<Person> it = peopleList.iterator();
		while (it.hasNext()) {
			Person p = it.next();
			if (! p.getName().contains(str))
				it.remove();
		}
		return peopleList;
	}
}

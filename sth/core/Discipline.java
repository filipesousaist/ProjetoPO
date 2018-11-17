package sth.core;

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import java.lang.Comparable;

import sth.core.Project;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.other.DuplicateProjectIdException;
import sth.core.exception.other.MaxStudentsException;
import sth.core.exception.other.MaxDisciplinesException;

import java.io.Serializable;

public class Discipline implements Serializable, Comparable<Discipline> {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;
	private int _capacity;

	private Set<Student> _students = new HashSet<>();
	private Set<Teacher> _teachers = new HashSet<>();
	private Set<Project> _projects = new HashSet<>();
	private Course _course;
	
	Discipline(String name) {
		_name = name;
		_capacity = -1; /* Unlimited */
	}

	Discipline(String name, int capacity) {
		_name = name;
		_capacity = capacity;
	}

	String getName() {
		return _name;
	}

	Course getCourse() {
		return _course;
	}

	Collection<Student> getStudents() {
		return Collections.unmodifiableSet(_students);
	}

	void setCourse(Course c) {
		_course = c;
	}

	void enrollStudent(Student s) {
		try {
			addStudent(s);
			s.addDiscipline(this);
		}
		catch (MaxStudentsException | MaxDisciplinesException e) {
			System.out.println(e.getMessage());
		}
	}

	void addStudent(Student s) throws MaxStudentsException {
		if (_students.size() == _capacity)
			throw new MaxStudentsException(_name);
		_students.add(s);
	}

	void addTeacher(Teacher t) {
		_teachers.add(t);
		t.addDiscipline(this);
	}

	void addProject(String projName) throws DuplicateProjectIdException {
		for (Project p: _projects)
			if (p.getName().equals(projName))
				throw new DuplicateProjectIdException(_name, projName);
		_projects.add(new Project(projName));
	}

	void closeProject(String projName)
		throws NoSuchProjectIdException {

		for (Project p: _projects){
			if (p.getName().equals(projName)){
				p.closeProject();
				return;
			}
		}
		throw new NoSuchProjectIdException(projName);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || ! (obj instanceof Discipline))
			return false;
		Discipline d = (Discipline) obj;
		return _name.equals(d._name) && _course.equals(d._course);
	}

	@Override
	public int compareTo(Discipline disc) {
		if (_course.equals(disc._course))
			return _name.compareTo(disc._name);
		return _course.compareTo(disc._course);
	} 
}
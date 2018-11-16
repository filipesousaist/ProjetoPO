package sth.core;

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import sth.core.Project;
import sth.core.exception.NoSuchProjectIdException;

import java.io.Serializable;

public class Discipline implements Serializable {
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
		if (_students.size() != _capacity && s.canEnrollDisciplines()) {
			_students.add(s);
			s.addDiscipline(this);
		}
	}

	void addTeacher(Teacher t) {
		_teachers.add(t);
		t.addDiscipline(this);
	}

	void addProject(String projName){
		Project p = new Project(projName);
		_projects.add(p);
	}

	void closeProject(String projName)
		throws NoSuchProjectIdException{

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
		return obj != null && 
			obj instanceof Discipline && 
			_name.equals(((Discipline) obj)._name);
	}
}
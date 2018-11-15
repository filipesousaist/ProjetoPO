package sth.core;

import java.util.Set;
import java.util.HashSet;

import java.io.Serializable;

public class Discipline implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;
	private int _capacity;

	private Set<Student> _students = new HashSet<>();
	private Set<Teacher> _teachers = new HashSet<>();
	private Course _course;
	
	Discipline(String name) {
		_name = name;
		_capacity = -1; /* Unlimited */
	}

	Discipline(String name, int capacity) {
		_capacity = capacity;
	}

	String getName() {
		return _name;
	}

	Course getCourse() {
		return _course;
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

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && 
			obj instanceof Discipline && 
			_name.equals(((Discipline) obj)._name);
	}
}
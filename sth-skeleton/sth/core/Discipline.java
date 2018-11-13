package sth.core;

import java.io.Serializable;

public class Discipline implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;
	private ArrayList<Student> _students;
	private Course _course;
	
	Discipline(String name) {
		_name = name;
	}

	public String toString() {
		return _name;
	}

	Course getCourse() {
		return _course;
	}
}
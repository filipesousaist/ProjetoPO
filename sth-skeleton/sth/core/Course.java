package sth.core;

import java.io.Serailizable;

public class Course implements Serailizable {

	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;

	private Set<Student> _students;
	private Set<Teacher> _teachers;
	private Set<Discipline> _disciplines;

	Course(String name) {
		_name = name;
	}

	boolean addDiscipline(Discipline d) {
		return _disciplines.add(d);
	}

	boolean addStudent(Student s) {
		return _students.add(s);
	}


}
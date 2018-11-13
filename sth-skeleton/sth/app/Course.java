package sth.core;

import java.io.Serailizable;

public class Course implements Serailizable {

	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;

	private ArrayList<Student> _students;
	private ArrayList<Teacher> _teachers;
	private ArrayList<Discipline> _disciplines;

	Course(String name) {
		_name = name;
	}


}
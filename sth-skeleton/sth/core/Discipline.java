package sth.core;

import java.io.Serializable;

public class Discipline implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private static final int MAX_STUDENTS = 30;
	private String _name;
	private Set<Student> _students = new HashSet<>();
	private Course _course;
	
	Discipline(String name) {
		_name = name;
	}

	public String toString() {
		return _name;
	}

	String getName() {
		return _name;
	}

	Course getCourse() {
		return _course;
	}

	void enrollStrudent(Student s) {
		_students.add(s);
		s.addDiscipline(this);
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj != NULL && 
			obj instanceof Discipline && 
			_name.equals(((Discipline) obj)._name);
	}
}
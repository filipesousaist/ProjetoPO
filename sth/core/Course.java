package sth.core;

import java.util.Set;
import java.util.HashSet;

import java.io.Serializable;

public class Course implements Serializable {

	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private static final int MAX_REPRESENTATIVES = 7;

	private String _name;

	private Set<Student> _students = new HashSet<>();
	private Set<Teacher> _teachers = new HashSet<>();
	private Set<Discipline> _disciplines = new HashSet<>();
	private Set<Student> _representatives = new HashSet<>();

	Course(String name) {
		_name = name;
	}

	void addDiscipline(Discipline d) {
		_disciplines.add(d);
		d.setCourse(this);
	}

	void addRepresentative(Student s) {
		_representatives.add(s);
	}

	void removeRepresentative(Student s) {
		_representatives.remove(s);
	}

	void enrollStudent(Student s) {
		if (s.isRepresentative())
			if (_representatives.size() < MAX_REPRESENTATIVES) {
				addRepresentative(s);
				_students.add(s);
			}
		else
			_students.add(s);
	}

	Discipline parseDiscipline(String disciplineName) {
		for (Discipline d: _disciplines)
			if (d.getName().equals(disciplineName))
				return d;

		Discipline newDiscipline = new Discipline(disciplineName);
		addDiscipline(newDiscipline);
		return newDiscipline;
	}

	public String getName() {
		return _name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && 
			obj instanceof Course && 
			_name.equals(((Course) obj)._name);
	}

}
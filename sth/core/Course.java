package sth.core;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.lang.Comparable;

import java.io.Serializable;

import sth.core.exception.other.MaxRepresentativesException;

public class Course implements Serializable, Comparable<Course> {

	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private static final int MAX_REPRESENTATIVES = 7;

	private String _name;

	private Set<Student> _students = new HashSet<>();
	private Set<Teacher> _teachers = new HashSet<>();
	private Set<Discipline> _disciplines = new HashSet<>();
	private List<Student> _representatives = new ArrayList<>();

	Course(String name) {
		_name = name;
	}

	String getName() {
		return _name;
	}

	void addDiscipline(Discipline d) {
		_disciplines.add(d);
	}

	boolean addRepresentative(Student s) 
		throws MaxRepresentativesException {

		if (_representatives.size() >= MAX_REPRESENTATIVES)
			throw new MaxRepresentativesException(_name);
		if (!_representatives.contains(s)) {
			_representatives.add(s);
			return true;
		}
		return false;
	}

	boolean removeRepresentative(Student s) {
		return _representatives.remove(s);
	}

	void enrollStudent(Student s) {
		if (s.isRepresentative()) {
			try {
				addRepresentative(s);
			}
			catch (MaxRepresentativesException mre) {
				System.out.println(mre.getMessage());
				return;
			}
		}
		_students.add(s);
	}

	Discipline parseDiscipline(String disciplineName) {
		for (Discipline d: _disciplines)
			if (d.getName().equals(disciplineName))
				return d;
		
		Discipline newDiscipline = new Discipline(this, disciplineName);
		addDiscipline(newDiscipline);
		return newDiscipline;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && 
			obj instanceof Course && 
			_name.equals(((Course) obj)._name);
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	@Override
	public int compareTo(Course c) {
		return _name.compareTo(c._name);
	}
}
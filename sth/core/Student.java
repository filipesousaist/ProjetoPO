package sth.core;

import sth.core.exception.BadEntryException;

import java.util.Set;
import java.util.TreeSet;

public class Student extends Person {
	private boolean _isRepresentative;

	private static final int MAX_DISCIPLINES = 6;

	private Course _course;
	private Set<Discipline> _disciplines = new TreeSet<>();

	Student(int id, String name, String phoneNumber, boolean rep) {
		super(id, name, phoneNumber);
		_isRepresentative = rep;
	}

	void setRepresentative(boolean rep) {
		_isRepresentative = rep;
		if (rep)
			_course.addRepresentative(this);
		else
			_course.removeRepresentative(this);
	}

	boolean isRepresentative() {
		return _isRepresentative;
	}

	void addDiscipline(Discipline d) {
		_disciplines.add(d);
	}

	boolean canEnrollDisciplines() {
		return _disciplines.size() < MAX_DISCIPLINES;
	}

	@Override
	String getPersonStr() {
		return _isRepresentative ? "DELEGADO" : "ALUNO";
	}

	@Override
	PersonType getPersonType() {
		return PersonType.STUDENT;
	}

	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] = lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		if (_course == null) {
			_course = school.parseCourse(components[0]);
			_course.enrollStudent(this);
		}

		Discipline dis = _course.parseDiscipline(components[1]);

		dis.enrollStudent(this);
	}

	@Override
	public String toString() {
		String s = super.toString();
		for (Discipline d: _disciplines)
			s += "* " + _course.getName() + " - " + d.getName() + "\n";
		return s;
	}

}

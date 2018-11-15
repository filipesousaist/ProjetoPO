package sth.core;

import sth.core.exception.BadEntryException;

import java.util.Set;
import java.util.HashSet;

import java.io.Serializable;

public class Teacher extends Person implements Serializable {

	private Set<Discipline> _disciplines = new HashSet<>();
	
	public Teacher(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
	}

	String getPersonType() {
		return "DOCENTE";
	}

	@Override
	void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] = lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		Course course = school.parseCourse(components[0]);
		Discipline discipline = course.parseDiscipline(components[1]);

		discipline.addTeacher(this);
	}

	void addDiscipline(Discipline d) {
		_disciplines.add(d);
	}

	@Override
	public String toString() {
		String s = super.toString();
		for (Discipline d: _disciplines)
			s += "\n* " + d.getCourse().toString() + " - " + d.toString();
		return s;
	}
}

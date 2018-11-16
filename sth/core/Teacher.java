package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.core.Discipline;
import sth.core.Student;

import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Teacher extends Person {

	private Set<Discipline> _disciplines = new HashSet<>();
	
	public Teacher(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
	}

	@Override 
	String getPersonStr() {
		return "DOCENTE";
	}

	@Override
	PersonType getPersonType() {
		return PersonType.TEACHER;
	}

	void addDiscipline(Discipline d) {
		_disciplines.add(d);
	}

	Discipline getDiscipline(String disciplineName)
		throws NoSuchDisciplineIdException {

		for (Discipline disc: _disciplines)
			if (disc.getName().equals(disciplineName))
				return disc;
		throw new NoSuchDisciplineIdException(disciplineName);
	}

	Collection<Student> getStudents(String disciplineName) 
		throws NoSuchDisciplineIdException {
			return getDiscipline(disciplineName).getStudents();
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

	@Override
	public String toString() {
		String s = super.toString();
		List<Discipline> sortedDisciplines = new ArrayList<>(_disciplines);
		Collections.sort(sortedDisciplines);
		for (Discipline d: sortedDisciplines)
			s += "* " + d.getCourse().getName() + " - " + d.getName() + "\n";
		return s;
	}
}
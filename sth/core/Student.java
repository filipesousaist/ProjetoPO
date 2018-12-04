package sth.core;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.other.MaxDisciplinesException;
import sth.core.exception.other.MaxRepresentativesException;

public class Student extends Person {
	private boolean _isRepresentative;

	private static final int MAX_DISCIPLINES = 6;

	private Course _course;
	private List<Discipline> _disciplines = new ArrayList<>();

	Student(int id, String name, String phoneNumber, boolean rep) {
		super(id, name, phoneNumber);
		_isRepresentative = rep;
	}

	boolean setRepresentative(boolean rep) {
		boolean result;
		try {
			if (rep)
				result = _course.addRepresentative(this);
			else
				result = _course.removeRepresentative(this);
			if (result)
				_isRepresentative = rep;
			return result;
		}
		catch (MaxRepresentativesException rlre) {
			System.out.println(rlre.getMessage());
			return false;
		}
	}

	boolean isRepresentative() {
		return _isRepresentative;
	}

	void addDiscipline(Discipline d) throws MaxDisciplinesException {
		if (_disciplines.size() >= MAX_DISCIPLINES)
			throw new MaxDisciplinesException(super.getName());
		if (! _disciplines.contains(d))
			_disciplines.add(d);
	}

	Course getCourse() {
		return _course;
	}

	String getPersonStr() {
		return _isRepresentative ? "DELEGADO" : "ALUNO";
	}

	PersonType getPersonType() {
		return PersonType.STUDENT;
	}

	void deliverProject(String disciplineName, String projectName, String message)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException {

		for (Discipline d: _disciplines)
			if (d.getName().equals(disciplineName)) {
				d.deliverProject(this, projectName, message);
				return;
			}
		throw new NoSuchDisciplineIdException(disciplineName);

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

		Collections.sort(_disciplines);

		for (Discipline d: _disciplines)
			s += "\n* " + _course.getName() + " - " + d.getName();
		return s;
	}
}

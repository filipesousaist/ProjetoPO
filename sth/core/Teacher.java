package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.DuplicateProjectIdException;

import sth.core.exception.survey.CoreNoSurveyException;

import sth.core.Discipline;
import sth.core.Student;

import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class Teacher extends Person {
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	private List<Discipline> _disciplines = new ArrayList<>();
	
	public Teacher(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
	}

	String getPersonStr() {
		return "DOCENTE";
	}

	PersonType getPersonType() {
		return PersonType.TEACHER;
	}

	void addDiscipline(Discipline d) {
		if (! _disciplines.contains(d))
			_disciplines.add(d);
	}

	private Discipline getDiscipline(String disciplineName)
		throws NoSuchDisciplineIdException {

		for (Discipline disc: _disciplines)
			if (disc.getName().equals(disciplineName))
				return disc;
		throw new NoSuchDisciplineIdException(disciplineName);
	}

	void createProject(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, DuplicateProjectIdException {

		getDiscipline(disciplineName).addProject(projectName);
	}

	void closeProject(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		
		getDiscipline(disciplineName).closeProject(projectName);
	}

	Project getProject(String disciplineName, String projectName)
		throws NoSuchDisciplineIdException, NoSuchProjectIdException {

		return getDiscipline(disciplineName).getProject(projectName);
	}

	Collection<Student> getStudents(String disciplineName) 
		throws NoSuchDisciplineIdException {
		
		return getDiscipline(disciplineName).getStudents();
	}

	String getSurveyResults(String disciplineName, String projectName) 
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException {

		return getDiscipline(disciplineName).getSurveyResultsFor(
			this, projectName);
	}

	String getFinishedSurveyResults(Project p) {
		Survey s = p.getSurvey();
		return " * Número de submissões: " + p.getNumberOfSubmissions() +
			"\n * Número de respostas: " + s.getNumberOfAnswers() + 
			"\n * Tempos de resolução (horas) (mínimo, médio, máximo): " + 
			s.getMinimumTime() + ", " + s.getMaximumTime() + ", " + 
			s.getAverageTime();
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

		Collections.sort(_disciplines);

		for (Discipline d: _disciplines)
			s += "\n* " + d.getCourse().getName() + " - " + d.getName();
		return s;
	}
}
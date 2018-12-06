package sth.core;

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import java.lang.Comparable;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.other.DuplicateProjectIdException;
import sth.core.exception.other.MaxStudentsException;
import sth.core.exception.other.MaxDisciplinesException;

import sth.core.exception.survey.CoreDuplicateSurveyException;
import sth.core.exception.survey.CoreNoSurveyException;

import java.io.Serializable;

public class Discipline implements Serializable, Comparable<Discipline> {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private final String _name;
	private final int _capacity;

	private final Course _course;
	private Set<Student> _students = new HashSet<>();
	private Set<Teacher> _teachers = new HashSet<>();
	private Set<Project> _projects = new HashSet<>();
	
	Discipline(Course course, String name) {
		_course = course;
		_name = name;
		_capacity = -1; /* Unlimited */
	}

	Discipline(Course course, String name, int capacity) {
		_course = course;
		_name = name;
		_capacity = capacity;
	}

	String getName() {
		return _name;
	}

	Course getCourse() {
		return _course;
	}

	Collection<Student> getStudents() {
		return Collections.unmodifiableSet(_students);
	}

	Collection<Teacher> getTeachers() {
		return Collections.unmodifiableSet(_teachers);
	}

	private Project getProject(String projectName) 
		throws NoSuchProjectIdException {

		for (Project p: _projects)
			if (p.getName().equals(projectName))
				return p;
		throw new NoSuchProjectIdException(projectName);
	}

	void enrollStudent(Student s) {
		try {
			addStudent(s);
			s.addDiscipline(this);
		}
		catch (MaxStudentsException | MaxDisciplinesException e) {
			System.out.println(e.getMessage());
		}
	}

	void addStudent(Student s) throws MaxStudentsException {
		if (_students.size() == _capacity)
			throw new MaxStudentsException(_name);
		_students.add(s);
	}

	void addTeacher(Teacher t) {
		_teachers.add(t);
		t.addDiscipline(this);
	}

	void addProject(String projectName) throws DuplicateProjectIdException {
		if (! _projects.add(new Project(this, projectName)))
			throw new DuplicateProjectIdException(_name, projectName);
	}

	void closeProject(String projectName) throws NoSuchProjectIdException {
		for (Project p: _projects){
			if (p.getName().equals(projectName)){
				p.close();
				return;
			}
		}
		throw new NoSuchProjectIdException(projectName);
	}

	void deliverProject(Student s, String projectName, String message)
		throws NoSuchProjectIdException {

		for (Project p: _projects) {
			if (p.getName().equals(projectName)) {
				p.addSubmission(s, message);
			}
		}
		throw new NoSuchProjectIdException(projectName);

	}

	void createSurvey(String projectName) throws NoSuchProjectIdException,
		CoreDuplicateSurveyException {
			
		getProject(projectName).createSurvey();
	}

	void openSurvey(String projectName) throws NoSuchProjectIdException,
		CoreNoSurveyException, CoreOpeningSurveyException {

		getProject(projectName).openSurvey();
	}

	void closeSurvey(String projectName) throws NoSuchProjectIdException,
		CoreNoSurveyException, CoreClosingSurveyException {

		getProject(projectName).closeSurvey();
	}

	void finishSurvey(String projectName) throws NoSuchProjectIdException,
		CoreNoSurveyException, CoreFinishingSurveyException {

		getProject(projectName).finishSurvey();
	}

	void cancelSurvey(String projectName) throws NoSuchProjectIdException,
		CoreNoSurveyException {

		getProject(projectName).cancelSurvey();
	}

	Collection<Survey> getSurveys() throws CoreNoSurveyException {
		List<Survey> _surveys = new ArrayList<>();
		for(Project p: _projects) {
			_surveys.add(p.getSurvey());
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || ! (obj instanceof Discipline))
			return false;
		Discipline d = (Discipline) obj;
		return _name.equals(d._name) && _course.equals(d._course);
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}

	@Override
	public int compareTo(Discipline disc) {
		if (_course.equals(disc._course))
			return _name.compareTo(disc._name);
		return _course.compareTo(disc._course);
	} 
}
package sth.core.project;

import java.io.Serializable;

import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import sth.core.Student;
import sth.core.Discipline;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.survey.CoreNoSurveyException;
import sth.core.exception.survey.CoreNonEmptySurveyException;

 public class Project implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;
	private String _description;
	private boolean _closed;

	private Discipline _discipline;
	private Survey _survey;

	private Set<Submission> _submissions = new HashSet<>();

	public Project(Discipline discipline, String name) {
		_discipline = discipline;
		_name = name;
		_closed = false;
	}

	public Project(Discipline discipline, String name, String descr) {
		this(discipline, name);
		_description = descr;
	}

	public Discipline getDiscipline(){
		return _discipline;
	}

	public String getName() {
		return _name;
	}

	public void close() {
		_closed = true;
	}

	public void addSubmission(Student student, String message) 
		throws NoSuchProjectIdException {

		if (_closed)
			throw new NoSuchProjectIdException(_name);

		Submission submission = new Submission(student, message);
		_submissions.remove(submission);
		_submissions.add(submission);
	}

	public Collection<Submission> getSubmissions() {
		return Collections.unmodifiableSet(_submissions);
	}

	public void removeSurvey() throws CoreNoSurveyException, 
		CoreNonEmptySurveyException {

		if (_survey == null)
			throw new CoreNoSurveyException(getDiscipline().getName(), 
				getName());
		else if (!_survey.isEmpty())
			throw new CoreNonEmptySurveyException(getDiscipline().getName(), 
				getName());

		_survey = null;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null &&
			obj instanceof Project &&
			_name.equals(((Project) obj)._name);
	}

	@Override
	public int hashCode() {
		return _name.hashCode();
	}
}
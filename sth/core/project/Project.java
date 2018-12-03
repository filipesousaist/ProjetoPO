package sth.core.project;

import java.io.Serializable;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import sth.core.Student;

 public class Project implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;
	private String _description;
	private boolean _closed;

	private Set<Submission> _submissions = new HashSet<>();

	public Project(String name) {
		_name = name;
		_closed = false;
	}

	public Project(String name, String descr) {
		this(name);
		_description = descr;
	}

	public String getName() {
		return _name;
	}

	public void close() {
		_closed = true;
	}

	public void addSubmission(Student s, String message) 
		throws NoSuchProjectIdException {

		if (_closed)
			throw new NoSuchProjectIdException(_name);

		Submission s = new Submission(s, message);
		_submissions.remove(s);
		_submissions.add(s);
	}

	public Collection<Submission> getSubmissions() {
		return new Collections.unmodifiableSet(_submissions);
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
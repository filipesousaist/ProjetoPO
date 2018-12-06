package sth.core;

import java.io.Serializable;

import sth.core.Student;

public class Submission implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private final Student _student;
	private final String _message;

	Submission(Student student, String message) {
		_student = student;
		_message = message;
	}

	Student getStudent() {
		return _student;
	}

	String getMessage() {
		return _message;
	}

	@Override
	public String toString() {
		return "* " + _student.getId() + " - " + _message;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && 
			obj instanceof Submission && 
			_student.equals(((Submission) obj)._student);
	}

	@Override
	public int hashCode() {
		return _student.hashCode();
	}
}
package sth.core;



public class Student extends Person implements java.io.Serializable {

	/** Serial number for serialization */

	boolean _isRepresentative;

	public Student(int id, String name, String phoneNumber, boolean rep) {
		super(id, name, phoneNumber);
		setRepresentative(rep);
	}

	void setRepresentative(boolean rep) {
		_isRepresentative = rep;
	}

	public String toString() {
		super("ALUNO");
	}
}
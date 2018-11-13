package sth.core;

public class Student extends Person {

	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private boolean _isRepresentative;
	private ArrayList<Discipline> _disciplines;

	Student(int id, String name, String phoneNumber, boolean rep) {
		super(id, name, phoneNumber);
		setRepresentative(rep);
	}

	void setRepresentative(boolean rep) {
		_isRepresentative = rep;
	}

	String getType() {
		return "ALUNO";
	}

	public String toString() {
		String s = super.toString();

		for (Discipline d: _disciplines)
			s += "\n* " + d.getCourse().toString() + " - " + d.toString();
	}
}
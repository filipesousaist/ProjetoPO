package sth.core;

public class Student extends Person {

	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private boolean _isRepresentative;
	private Set<Discipline> _disciplines;

	Student(int id, String phoneNumber, String name, boolean rep) {
		super(id, name, phoneNumber);
		_isRepresentative = rep;
		_disciplines = new HashSet<>();
	}

	void setRepresentative(boolean rep) {
		_isRepresentative = rep;
	}

	@Override
	String getPersonType() {
		return _isRepresentative ? "DELEGADO" : "ALUNO";
	}

	@Override
  void parseContext(String lineContext, School school) throws BadEntryException {
    String components[] =  lineContext.split("\\|");

    if (components.length != 2)
      throw new BadEntryException("Invalid line context " + lineContext);

    if (_course == null) {
      _course = school.parseCourse(components[0]);
      _course.addStudent(this);
    }

    Discipline dis = _course.parseDiscipline(components[1]);

    dis.enrollStudent(this);
  }

	@Override
	public String toString() {
		String s = super.toString();

		for (Discipline d: _disciplines)
			s += "\n* " + d.getCourse().toString() + " - " + d.toString();
	}

}
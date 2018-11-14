package sth.core;

import java.io.Serailizable;

public class Course implements Serailizable {

	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private String _name;

	private Set<Student> _students = new HashSet<>();
	private Set<Teacher> _teachers = new HashSet<>();
	private Set<Discipline> _disciplines = new HashSet<>();

	Course(String name) {
		_name = name;
	}

	boolean addDiscipline(Discipline d) {
		return _disciplines.add(d);
	}

	void addStudent(Student s) {
		_students.add(s);
	}

	Discipline parseDiscipline(String disciplineName) {
    for (Discipline d: _disciplines)
      if (d.getName().equals(disciplineName))
        return d;

    Discipline newDiscipline = new Discipline(disciplineName);
    addDiscipline(newDiscipline);
    return newDiscipline;
  }

	public String getName() {
		return _name;
	}


}
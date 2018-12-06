package sth.core;

import java.io.Serializable;

public class Employee extends Person {
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;
	
	public Employee(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
	}

	String getPersonStr() {
		return "FUNCIONÁRIO";
	}

	PersonType getPersonType() {
		return PersonType.EMPLOYEE;
	}
}
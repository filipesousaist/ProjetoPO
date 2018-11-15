package sth.core;

import java.io.Serializable;

public class Employee extends Person {
	
	public Employee(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
	}

	String getPersonType() {
		return "FUNCIONÁRIO";
	}
}
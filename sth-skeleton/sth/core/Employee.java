package sth.core;


public class Employee extends Person implements java.io.Serializable {
	
	/* Serial number for serialization */
	
	public Employee(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
	}
	
}
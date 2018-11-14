package sth.core;


public class Teacher extends Person implements java.io.Serializable {
	
	/* Serial number for serialization */
	
	public Teacher(int id, String name, String phoneNumber) {
		super(id, name, phoneNumber);
	}

	
}

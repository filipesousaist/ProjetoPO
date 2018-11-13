package sth.core;

// Imports aqui!!

public abstract class Person implements java.io.Serializable {
	
	/** Serial number for serialization. */
	
	private int _id;
	private String _name;
	private String _phoneNumber;
	
	Person(int id, String name, String phoneNumber){
		_id = id;
		_name = name;
		_phoneNumber = phoneNumber;
	}
	
	public int getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getPhoneNumber() {
		return _phoneNumber;
	}
	
	void changePhoneNumber(String newPhoneNumber) {
		_phoneNumber = newPhoneNumber;
	}
	
	public abstract String getType();
}









package sth.core;

import sth.core.exception.BadEntryException;

import java.io.Serializable;

public abstract class Person implements Serializable {
	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;
	
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
	
	String getPhoneNumber() {
		return _phoneNumber;
	}

	abstract String getPersonType();
	
	void changePhoneNumber(String newPhoneNumber) {
		_phoneNumber = newPhoneNumber;
	}

	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Should not have extra context: " + context);
	}
	
	public String toString() {
		return getPersonType() + "|" + getId() + "|" + 
			getPhoneNumber() + "|" + getName() + "\n";
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null &&
			obj instanceof Person && 
			_id == ((Person) obj).getId();
	}
}
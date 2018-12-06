package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

import sth.core.exception.survey.CoreNoSurveyException;

import java.util.List;
import java.util.ArrayList;

import java.io.Serializable;

public abstract class Person implements Serializable, Comparable<Person>,
	Observer {
	
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;
	
	private final int _id;
	private final String _name;
	private String _phoneNumber;
	private List<Notification> _notifications = new ArrayList<>();
	
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

	abstract PersonType getPersonType();

	abstract String getPersonStr();
	
	void changePhoneNumber(String newPhoneNumber) {
		_phoneNumber = newPhoneNumber;
	}

	void parseContext(String context, School school) throws BadEntryException {
		throw new BadEntryException("Should not have extra context: " + context);
	}
	
	public void update(Subject subject) {
		Survey survey = (Survey) subject;
		_notifications.add(survey.getNotification());
	}

	String getSurveyResults(String disciplineName, String projectName) 
		throws NoSuchDisciplineIdException, NoSuchProjectIdException,
		CoreNoSurveyException {
		
		return null;
	}

	String getFinishedSurveyResults(Project p) {
		return null;
	}


	@Override
	public String toString() {
		return getPersonStr() + "|" + getId() + "|" + 
			getPhoneNumber() + "|" + getName();
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null &&
			obj instanceof Person && 
			_id == ((Person) obj).getId();
	}

	@Override
	public int hashCode() {
		return _id;
	}

	@Override
	public int compareTo(Person p) {
		return _id - p._id;
	}
}
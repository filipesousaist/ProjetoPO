package sth.core;

import java.io.Serializable;

import java.util.Set;
import java.util.HashSet;

public abstract class Subject implements Serializable {
	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	private Set<Observer> _observers = new HashSet<>();

	public void attach(Person p) {
		_observers.add(p);
	}

	public void detach(Person p) {
		_observers.remove(p);
	}

	public void notifyObservers(){
		for (Observer o: _observers) 
			o.update(this);
	}
}
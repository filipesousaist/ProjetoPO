package sth.core;

import java.util.List;
import java.util.ArrayList;


public abstract class Subject {

	private List<Observer> _observers = new ArrayList<>();

	public void attach(Person p) {
		_observers.add(p);
	}

	public void detach(Person p) {
		_observers.remove(p);
	}

	public void notifyObserver(){
		for (Observer o: _observers) 
			o.update(this);
	}
}
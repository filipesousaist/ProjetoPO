package sth.core;

import java.io.Serializable;

public class Answer implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private final int _hours;
	private final String _message;

	Answer(int hours, String message) {
		_hours = hours;
		_message = message;
	}

	int getHours(){
		return _hours;
	}

	String getMessage(){
		return _message;
	}
}
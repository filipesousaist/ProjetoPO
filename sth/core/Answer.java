package sth.core;

import java.io.Serializable;

public class Answer implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private final int _hours;
	private final String _message;

	public Answer(int hours, String message) {
		_hours = hours;
		_message = message;
	}

	public int getHours(){
		return _hours;
	}

	public String getMessage(){
		return _message;
	}
}
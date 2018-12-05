package sth.core;

public class Answer {

	private String _message;
	private int _hours;

	public Answer(String message, int hours) {
		_message = message;
		_hours = hours;
	}

	public String getMessage(){
		return _message;
	}

	public int getHours(){
		return _hours;
	}
}
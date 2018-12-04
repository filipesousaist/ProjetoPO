package sth.core.project;

public class Answer {

	private String _message;
	private int _hours;

	public Answer(String message, Int hours) {
		_message = message;
		_hours = hours;
	}

	public String getMessage(){
		return _message;
	}

	public Int getHours(){
		return _hours;
	}
}
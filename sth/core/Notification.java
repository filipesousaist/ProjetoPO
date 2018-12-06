package sth.core;

public class Notification {

	private final String _message;

	Notification(String message) {
		_message = message;
	}

	public String getMessage() {
		return _message;
	}
}
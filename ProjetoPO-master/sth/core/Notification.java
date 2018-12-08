package sth.core;

import java.io.Serializable;

public class Notification implements Serializable {
	/** Serial number for serialization */
	private static final long serialVersionUID = 201810051538L;

	private final String _message;

	Notification(String message) {
		_message = message;
	}

	public String getMessage() {
		return _message;
	}
}
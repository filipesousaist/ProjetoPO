package sth.core.exception.other;

public class WrongCourseException extends Exception {
	/** Class serial number. */
  	private static final long serialVersionUID = 201409301048L;

  	private String _discName;
  	private String _rightCourseName;
  	private String _wrongCourseName;

  	public WrongCourseException(String discName, String rightCourseName, 
  		String wrongCourseName) {
  		_discName = discName;
  		_rightCourseName = rightCourseName;
  		_wrongCourseName = wrongCourseName;
  	}

  	public String getMessage() {
  		return "Tried to add discipline " + _discName + " to course " +
  			_wrongCourseName + ", but it belongs to course " + 
  			_rightCourseName + ".";
  	}
}
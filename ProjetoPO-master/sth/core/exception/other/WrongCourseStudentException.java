package sth.core.exception.other;

public class WrongCourseStudentException extends Exception {
	/** Class serial number. */
  	private static final long serialVersionUID = 201409301048L;

    private String _studentName;
  	private String _discName;
  	private String _studentCourseName;
    private String _discCourseName;


  	public WrongCourseStudentException(String studentName, String discName,
      String studentCourseName, String discCourseName) {
      _studentName = studentName;
  		_discName = discName;
  		_discCourseName = discCourseName;
  		_studentCourseName = studentCourseName;
  	}

  	public String getMessage() {
  		return "Can't add student " + _studentName + " of course " +
  			_studentCourseName + " to discipline " + 
  			_discName + " of course " + _discCourseName + ".";
  	}
}
package sth.core.exception.other;

public class MaxRepresentativesException extends Exception {
  /** Class serial number. */
    private static final long serialVersionUID = 201409301048L;

    private String _courseName;

    public MaxRepresentativesException(String courseName) {
      _courseName = courseName;
    }

    public String getMessage() {
      return "Can't add representative to course " + _courseName + 
        ": limit reached.";
    }
}
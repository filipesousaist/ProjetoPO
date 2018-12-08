package sth.core.exception.other;

public class MaxStudentsException extends Exception {
  /** Class serial number. */
    private static final long serialVersionUID = 201409301048L;

    private String _discName;

    public MaxStudentsException(String discName) {
      _discName = discName;
    }

    public String getMessage() {
      return "Can't add student to discipline " + _discName + 
        ": limit reached.";
    }
}
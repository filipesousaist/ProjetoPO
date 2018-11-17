package sth.core.exception.other;

public class MaxDisciplinesException extends Exception {
  /** Class serial number. */
    private static final long serialVersionUID = 201409301048L;

    private String _studentName;

    public MaxDisciplinesException(String studentName) {
      _studentName = studentName;
    }

    public String getMessage() {
      return "Can't enroll student " + _studentName + 
        " in a new discipline: limit reached.";
    }
}
package sth.core.exception;

public class DuplicateProjectIdException extends Exception {
  /** Class serial number. */
    private static final long serialVersionUID = 201409301048L;

    private String _discName;
    private String _projName;
    

    public DuplicateProjectIdException(String discName, String projName) {
      _discName = discName;
      _projName = projName;
    }

    public String getDiscName() {
      return _discName;
    }

    public String getProjName() {
      return _projName;
    }
}
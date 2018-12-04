package sth.core.exception.survey;

public class OpeningException extends ProjectException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  /**
   * @param discipline 
   * @param project 
   */
  public OpeningException(String discipline, String project) {
   super(discipline, project);
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.openingSurvey(_discipline, _project);
  }

}

package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.survey.CoreDuplicateSurveyException;

import sth.app.exception.DuplicateSurveyException;

/**
 * 4.5.1. Create survey.
 */
public class DoCreateSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCreateSurvey(SchoolManager receiver) {
    super(Label.CREATE_SURVEY, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */ 
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    String disciplineName = _discipline.value();
    String projectName = _project.value();
    try {
      _receiver.createSurvey(disciplineName, projectName);
    }
    catch (CoreDuplicateSurveyException cdse) {
      throw new DuplicateSurveyException(disciplineName, projectName);
    }
  }
}

package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

import sth.core.exception.survey.CoreNoSurveyException;

import sth.app.exception.NoSurveyException;

/**
 * 4.5.3. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, NoSurveyException {
    String disciplineName = _discipline.value();
    String projectName = _project.value();

    try {
      _receiver.showSurveyResults(disciplineName, projectName);
    }
    catch (CoreNoSurveyException cnse) {
      throw new NoSurveyException(disciplineName, projectName);
    }
  }

}

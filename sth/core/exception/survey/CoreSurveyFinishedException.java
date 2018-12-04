package sth.core.exception.survey;

public class CoreSurveyFinishedException extends CoreSurveyException {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	public CoreSurveyFinishedException(String discipline, String project) {
		super(discipline, project);
	}
}
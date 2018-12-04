package sth.core.exception.survey;

public class CoreNonEmptySurveyException extends CoreSurveyException {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	public CoreNonEmptySurveyException(String discipline, String project) {
		super(discipline, project);
	}
}
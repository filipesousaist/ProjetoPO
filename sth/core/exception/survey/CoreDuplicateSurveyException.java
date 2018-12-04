package sth.core.exception.survey;

public class CoreDuplicateSurveyException extends CoreSurveyException {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	public CoreDuplicateSurveyException(String discipline, String project) {
		super(discipline, project);
	}

}
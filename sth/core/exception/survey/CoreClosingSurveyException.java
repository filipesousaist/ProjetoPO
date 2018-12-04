package sth.core.exception.survey;

public class CoreClosingSurveyException extends CoreSurveyException {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	public CoreClosingSurveyException(String discipline, String project) {
		super(discipline, project);
	}

}
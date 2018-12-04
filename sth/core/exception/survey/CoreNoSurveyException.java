package sth.core.exception.survey;

public class CoreNoSurveyException extends CoreSurveyException {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	public CoreNoSurveyException(String discipline, String project) {
		super(discipline, project);
	}
}
package sth.core.exception.survey;

public class CoreFinishingSurveyException extends CoreSurveyException {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	public CoreFinishingSurveyException(String discipline, String project) {
		super(discipline, project);
	}

}
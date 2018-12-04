package sth.core.exception.survey;

public abstract class CoreSurveyException extends Exception {
	private String _disciplineName;
	private String _projectName;

	public CoreSurveyException(String disciplineName, String projectName) {
		_disciplineName = disciplineName;
		_projectName = projectName;
	}

	public String getDisciplineName() {
		return _disciplineName;
	}

	public String getProjectName() {
		return _projectName;
	}
}
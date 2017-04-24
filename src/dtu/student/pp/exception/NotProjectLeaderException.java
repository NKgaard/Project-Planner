package dtu.student.pp.exception;

public class NotProjectLeaderException extends Exception {
	public NotProjectLeaderException() {
		//Maybe add the logged in user, the project and the current project leader to error message?
		super("User must be the project leader to perform this action!");
	}
}

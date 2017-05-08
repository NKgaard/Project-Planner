package dtu.student.pp.exception;

/**
 * 
 * @author Jonas Schjønnemann (s151781)
 *
 */
@SuppressWarnings("serial")
public class NotProjectLeaderException extends Exception {
	public NotProjectLeaderException() {
		//Maybe add the logged in user, the project and the current project leader to error message?
		super("User must be the project leader to perform this action!");
	}
}

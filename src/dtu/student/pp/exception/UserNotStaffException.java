package dtu.student.pp.exception;

/**
 * 
 * @author Jonas Schjønnemann (s151781)
 *
 */
@SuppressWarnings("serial")
public class UserNotStaffException extends Exception {
	public UserNotStaffException() {
		super("You have to be part of the staff for this activity to register work hours!");
	}
}

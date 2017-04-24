package dtu.student.pp.exception;

public class UserNotStaffException extends Exception {
	public UserNotStaffException() {
		super("You have to be part of the staff for this activity to register work hours!");
	}
}

package dtu.student.pp.exception;

public class UserAlreadyExistsException extends Exception {
	public UserAlreadyExistsException() {
		super("There already exists a user with these initials!");
	}
}

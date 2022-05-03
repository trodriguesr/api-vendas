package trodriguesr.com.github.sales.exception;

public class InvalidPasswordException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException() {
		super("Invalid Password");
	}
	

}

package trodriguesr.com.github.sales.exception;

public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException() {
		super("Order not found.");
		
	}
	
	
	

}

package trodriguesr.com.github.sales.errors;

import java.util.Arrays;
import java.util.List;

public class ErrorsApi {

	private List<String> errors;

	public ErrorsApi(String errorMessage) {
		this.errors = Arrays.asList(errorMessage);
	}
	
	public ErrorsApi(List<String> errors) {
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

}

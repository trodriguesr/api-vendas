package trodriguesr.com.github.sales.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import trodriguesr.com.github.sales.errors.ErrorsApi;
import trodriguesr.com.github.sales.exception.BusinessRulesException;
import trodriguesr.com.github.sales.exception.OrderNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(BusinessRulesException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorsApi handleBusinessRulesException(BusinessRulesException ex){
        String errorMessage = ex.getMessage();
        return new ErrorsApi(errorMessage);
    }
	
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorsApi handleOrderNotFoundException(OrderNotFoundException ex) {
		
		return new ErrorsApi(ex.getMessage());
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorsApi handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		
		List<String> errors = ex.getBindingResult().getAllErrors().stream()
							.map( error -> error.getDefaultMessage() )
							.collect(Collectors.toList());
		
		return new ErrorsApi(errors);
		
	}

}

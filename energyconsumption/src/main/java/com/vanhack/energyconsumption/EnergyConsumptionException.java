package com.vanhack.energyconsumption;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles Exceptions in Controller 
 */
@ControllerAdvice
public class EnergyConsumptionException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	
	public EnergyConsumptionException() {}
	
	public EnergyConsumptionException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Exception handler if EnergyConsumptionException is thrown in this Application
	 *
	 * @param ex
	 * @return Error message String.
	 */
	@ExceptionHandler(EnergyConsumptionException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String return400(EnergyConsumptionException ex) {
		return ex.errorMessage;
	}
	
	/**
	 * Exception handler if MethodArgumentNotValidException is thrown in this Application
	 *
	 * @param ex
	 * @return Error
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }
	
	private Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "validation error");
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }
	
	static class Error {
        private final int status;
        private final String message;
        private List<FieldError> fieldErrors = new ArrayList<>();

        Error(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public void addFieldError(String path, String message) {
            FieldError error = new FieldError("",path, message);
            fieldErrors.add(error);
        }

        public List<FieldError> getFieldErrors() {
            return fieldErrors;
        }
    }
}

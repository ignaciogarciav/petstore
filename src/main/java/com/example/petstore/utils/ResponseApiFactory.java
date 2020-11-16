package com.example.petstore.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.generated.model.ResponseApi;
import com.example.petstore.exceptions.ResourceNotFoundException;

@Component
public class ResponseApiFactory extends ResponseApi {

	public ResponseApi createNew201Response() {
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._201_CREATED);
		response.setMessage("Succesfully created");
		return response;
	}

	public ResponseApi createNew200Response() {
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._200_OK);
		response.setMessage("Succesful action");
		return response;
	}

	public ResponseApi createNew404Response(ResourceNotFoundException ex) {
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._404_NOT_FOUND);
		response.setMessage(ex.getMessage());
		return response;
	}

	public ResponseApi createNew500Response() {
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._500_INTERNAL_SERVER_ERROR);
		response.setMessage("Server error");
		return response;
	}

	public ResponseApi createNewNullPointerExceptionResponse(NullPointerException ex) {
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._400_BAD_REQUEST);
		response.setMessage(ex.getMessage());
		return response;
	}

	public ResponseApi createNewInvalidTypeResponse(MethodArgumentTypeMismatchException ex) {
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._400_BAD_REQUEST);
		response.setMessage(ex.getName() + " should be type of " + ex.getRequiredType().getName());
		return response;
	}

	public ResponseApi createNewUniqueConstraintViolationResponse(
			org.hibernate.exception.ConstraintViolationException ex) {
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._400_BAD_REQUEST);
		response.setMessage(ex.getCause().getLocalizedMessage().substring(0, 37));
		return response;
	}

	public Map<String, ResponseApi> createNewMethodArgumentViolationResponse(MethodArgumentNotValidException ex) {
		Map<String, ResponseApi> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			ResponseApi response = new ResponseApi();
			response.setTimestamp(new Date());
			response.setStatus(StatusEnum._400_BAD_REQUEST);
			response.setMessage(ex.getBindingResult().getFieldError(fieldName).getDefaultMessage());
			errors.put(fieldName, response);
		});
		return errors;

	}

	public Map<String, ResponseApi> createNewConstraintViolationExceptionResponse(ConstraintViolationException ex) {
		Map<String, ResponseApi> errors = new HashMap<>();
		ex.getConstraintViolations().forEach(violation -> {
			String fieldname = violation.getPropertyPath().toString();
			ResponseApi response = new ResponseApi();
			response.setTimestamp(new Date());
			response.setStatus(StatusEnum._400_BAD_REQUEST);
			response.setMessage(violation.getMessage());
			errors.put(fieldname, response);
		});
		return errors;
	}

	public ResponseApi createNewMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		ResponseApi response = new ResponseApi();
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._400_BAD_REQUEST);
		response.setMessage(builder.toString());
		return response;
	}

	public ResponseApi createNewRollBackExceptionResponse(RollbackException ex){
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._400_BAD_REQUEST);
		response.setMessage(ex.getCause().getMessage());
		return response;
	}

}

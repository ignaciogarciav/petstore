package com.example.petstore.exceptions;

import java.util.Map;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.generated.model.ResponseApi;
import com.example.petstore.utils.ResponseApiFactory;

@ControllerAdvice
public class MyExceptionHandler {
	@Autowired
	private ResponseApiFactory responseFactory;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseApi> resourceNotFound(ResourceNotFoundException ex) {
		return new ResponseEntity<ResponseApi>(responseFactory.createNew404Response(ex), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseApi> globalExceptionHandler() {
		return new ResponseEntity<ResponseApi>(responseFactory.createNew500Response(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, ResponseApi>> handleValidationExceptions(MethodArgumentNotValidException ex) {

		return new ResponseEntity<Map<String, ResponseApi>>(
				responseFactory.createNewMethodArgumentViolationResponse(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ResponseApi> handleInvalidStatusExceptions(NullPointerException ex) {
		return new ResponseEntity<ResponseApi>(responseFactory.createNewNullPointerExceptionResponse(ex),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseApi> handleInvalidTypeExceptions(MethodArgumentTypeMismatchException ex) {
		return new ResponseEntity<ResponseApi>(responseFactory.createNewInvalidTypeResponse(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseApi> handleConstraintViolations(org.hibernate.exception.ConstraintViolationException ex) {
		return new ResponseEntity<ResponseApi>(responseFactory.createNewUniqueConstraintViolationResponse(ex),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
		return new ResponseEntity<Object>(responseFactory.createNewConstraintViolationExceptionResponse(ex), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ResponseApi> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		return new ResponseEntity<ResponseApi>(responseFactory.createNewMethodNotSupportedException(ex), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<Object> handleRollBackExceptions(RollbackException ex){
		return new ResponseEntity<Object>(responseFactory.createNewRollBackExceptionResponse(ex),HttpStatus.BAD_REQUEST);
	}
}
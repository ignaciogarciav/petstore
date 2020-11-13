package com.example.petstore.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
	public ResponseApi createNewInvalidStatusResponse() {
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._400_BAD_REQUEST);
		response.setMessage("Invalid status");
		return response;
	}
	public ResponseApi createNewInvalidTypeResponse() {
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._400_BAD_REQUEST);
		response.setMessage("Invalid criteria");
		return response;
	}
	
	public ResponseApi createNewUniqueConstraintViolationResponse(){
		ResponseApi response = new ResponseApi();
		response.setTimestamp(new Date());
		response.setStatus(StatusEnum._400_BAD_REQUEST);
		response.setMessage("Some fields are not unique!");
		return response;
	}
	
	public Map<String, ResponseApi> createNewMethodArgumentViolationResponse(MethodArgumentNotValidException ex){
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
}

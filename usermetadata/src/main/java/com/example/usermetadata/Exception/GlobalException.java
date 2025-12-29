package com.example.usermetadata.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalException {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> UserExceptionHandler(UserException ue, WebRequest wreq){
		ErrorDetails err = new ErrorDetails(ue.getMessage(), wreq.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails> (err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> OtherExceptionHandler(Exception e, WebRequest wreq){
		ErrorDetails err = new ErrorDetails(e.getMessage(), wreq.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails> (err, HttpStatus.BAD_REQUEST);
	}
}

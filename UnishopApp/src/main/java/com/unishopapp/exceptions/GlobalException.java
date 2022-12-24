package com.unishopapp.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
	
	
	 @ExceptionHandler(ProductException.class)
	    public ResponseEntity<MyErrorDetails> ExceptionHandler(ProductException exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(CategoryException.class)
	    public ResponseEntity<MyErrorDetails> ExceptionHandler(CategoryException exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(CartException.class)
	    public ResponseEntity<MyErrorDetails> ExceptionHandler(CartException exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(PaymentException.class)
	    public ResponseEntity<MyErrorDetails> ExceptionHandler(PaymentException exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(OrderException.class)
	    public ResponseEntity<MyErrorDetails> ExceptionHandler(OrderException exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(CustomerException.class)
	    public ResponseEntity<MyErrorDetails> ExceptionHandler(CustomerException exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(ReportException.class)
	    public ResponseEntity<MyErrorDetails> ExceptionHandler(ReportException exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(FeedbackException.class)
	    public ResponseEntity<MyErrorDetails> ExceptionHandler(FeedbackException exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	 @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<MyErrorDetails> HotelExceptionHandler(RuntimeException exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	    }
	 
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception exp, WebRequest req) {
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	
	 @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<MyErrorDetails> IllegalArgumentExceptionHandler(IllegalArgumentException exp, WebRequest req) {
	                                                                                       
	        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
	        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	    }
	
	
	
}

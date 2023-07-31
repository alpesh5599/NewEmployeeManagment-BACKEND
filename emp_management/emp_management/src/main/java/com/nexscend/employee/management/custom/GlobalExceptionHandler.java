package com.nexscend.employee.management.custom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<String> handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
        String errorMessage = "File isn't selected, Please select the file";
        return ResponseEntity.badRequest().body(errorMessage);
    }
}

package com.java.weatherAlert.Exception;

import java.net.http.HttpHeaders;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNodataFoundException(
        NoDataFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "No Data found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    } 
    
    @ExceptionHandler(CreateFailedException.class)
    public ResponseEntity<Object> handleNodataFoundException(
        CreateFailedException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Failed to create");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    } 

    //@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, 
        HttpStatus status, WebRequest request) {
    	
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
    protected ResponseEntity<Object> handleInternalServerError(
            InternalServerError ex, HttpHeaders headers, 
            HttpStatus status, WebRequest request) {
        	
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDate.now());
            body.put("status", status.value());

            String errors = ex.getMessage();
                   

            body.put("Error Message", errors);

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
     }
    
    
    
    protected ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(
    		SQLIntegrityConstraintViolationException ex, HttpHeaders headers, 
            HttpStatus status, WebRequest request) {
    	
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDate.now());
            body.put("status", status.value());

            String errors = ex.getMessage();
                   

            body.put("Error Message", errors);

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
     }
    
    protected ResponseEntity<Object> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpHeaders headers, 
            HttpStatus status, WebRequest request) {
        	
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDate.now());
            body.put("status", status.value());

            String errors = ex.getMessage();
                   

            body.put("Error Message", errors);

            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
     }
}
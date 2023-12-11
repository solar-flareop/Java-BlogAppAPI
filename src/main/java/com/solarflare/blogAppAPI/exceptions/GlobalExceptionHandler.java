package com.solarflare.blogAppAPI.exceptions;

import com.solarflare.blogAppAPI.payloads.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        APIResponse apiRes = new APIResponse(ex.getMessage(), false);
        return new ResponseEntity<>(apiRes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> respMsg = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((objectError)->{
            String fieldName = ((FieldError)objectError).getField();
            String message = objectError.getDefaultMessage();
            respMsg.put(fieldName,message);
        });
        return new ResponseEntity<>(respMsg,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<APIResponse> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex){
        APIResponse apiRes = new APIResponse(ex.getMessage(),false);
        return new ResponseEntity<>(apiRes,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<APIResponse> NoResourceFoundExceptionHandler(NoResourceFoundException ex){
        APIResponse apiRes = new APIResponse(ex.getMessage(),false);
        return new ResponseEntity<>(apiRes,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex){
        APIResponse apiRes = new APIResponse(ex.getMessage(),false);
        return new ResponseEntity<>(apiRes,HttpStatus.BAD_REQUEST);
    }
}

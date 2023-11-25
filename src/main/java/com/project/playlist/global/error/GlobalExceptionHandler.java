package com.project.playlist.global.error;


import com.project.playlist.global.error.exception.ErrorCode;
import com.project.playlist.global.error.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(GlobalException e){
        ErrorCode errorCode =e.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode.getStatus(),errorCode.getMessage()),
                                    HttpStatus.valueOf(errorCode.getStatus()));
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindException(BindException e){
        Map<String,String> errorMap = new HashMap<>();
        for(FieldError error : e.getFieldErrors()){
            errorMap.put(error.getField(),error.getDefaultMessage());
        }
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException e){
        Map<String,String> errorMap = new HashMap<>();
        int i = 0;

        for(ConstraintViolation error : e.getConstraintViolations()){
            errorMap.put("error"+i,error.getMessageTemplate());
            i++;
        }
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

}
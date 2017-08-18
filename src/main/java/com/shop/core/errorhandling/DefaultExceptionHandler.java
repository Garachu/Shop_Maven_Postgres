package com.shop.core.errorhandling;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by meg on 7/21/17.
 */

//Spring 3.2 brings support for a global @ExceptionHandler with the new @ControllerAdvice annotation.

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request){
        log.error("handleHttpMessageNotReadable=======================================");
        log.error("getMessage()========== "+ ex.getMessage());
        String message = "";
        List<String> errors = new ArrayList<>();
        if (ex.getCause() instanceof JsonMappingException){
            log.error("JsonMappingException========== ");
            JsonMappingException e = (JsonMappingException) ex.getCause();
            log.error("getMessage()================== " + e.getMessage());
            log.error("getCause().getMessage()======= " + e.getCause().getMessage());
            log.error("getLocalizedMessage()========= " + e.getLocalizedMessage());
            log.error(e.getCause().toString());
            if(e.getCause() instanceof DateTimeParseException){
                log.error("DateTimeParseException===========");
                DateTimeParseException exception = (DateTimeParseException) e.getCause();
                log.error("getParsedString()====== " + exception.getParsedString());
                log.error("getMessage============= " + exception.getMessage());
            }
            message = e.getCause().getMessage();

        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, message, errors);
        return new ResponseEntity<Object>(apiError, headers, status);

    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    @ResponseBody
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex){
        log.error("InvalidFormatException");
        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "hfhhfhhf");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleConflictException(DataIntegrityViolationException ex){
        log.error("DataIntegrityViolationException" + ex.getMessage());
        ApiError apiError;
        if(ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException){
            org.hibernate.exception.ConstraintViolationException e = (org.hibernate.exception.ConstraintViolationException) ex.getCause();
            log.error("ConstraintViolationException======" + e.getMessage());
            log.error("getConstraintName=================" + e.getConstraintName());
            log.error("getLocalizedMessage===============" + e.getLocalizedMessage());
            log.error("getErrorCode======================" + e.getErrorCode());
            log.error("toString==========================" + e.toString());
            log.error("getSQLException().getMessage()====" + e.getSQLException().getMessage());
            apiError =
                    new ApiError(HttpStatus.CONFLICT, e.getSQLException().getMessage(), "");
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }
        apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "hfhhfhhf");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }


}


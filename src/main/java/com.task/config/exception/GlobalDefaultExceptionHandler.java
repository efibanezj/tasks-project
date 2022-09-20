package com.task.config.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private final ExceptionMessage exceptionMessage;

    public GlobalDefaultExceptionHandler(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(NotFoundException e) {

        log.error("Error [{}]", e.getMessage());
        String message = exceptionMessage.getMessage(e.getErrorCode());
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).errorMessage(message).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(BusinessException e) {

        log.error("Error [{}]", e.getMessage());
        String message = exceptionMessage.getMessage(e.getErrorCode());
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}

package com.example.formatter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * Global exception handler to manage errors
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles media type not supported exceptions
     * @param ex the exception
     * @return bad request response 
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> handlerMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        // Return error message when content type is not xml
        return ResponseEntity.badRequest().body("Error: You must send XML content (Content-Type: application/xml)");
    }

    /**
     * Handler multipar and missing request part exections
     * @return error message for file isses 
     */
    @ExceptionHandler({ MultipartException.class, MissingServletRequestPartException.class })
    public ResponseEntity<String> handlerMultiparError() {
        // Return error when file is missing or request is wrong
        return ResponseEntity.badRequest()
                .body("Error: The file has not been sent or the request is not of type 'form-data'");
    }
}

package com.example.formatter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * Global exception handler to manage errors
 * Global Exception Handler para manejar los errores
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles media type not supported exceptions
     * Maneja las excepciones de tipo de medio no soportado
     * 
     * @param ex the exception / la excepcion
     * @return bad request response / respuesta de peticion incorrecta
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> handlerMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        // Return error message when content type is not xml
        // Retorna mensaje de error cuando el tipo de contenido no es xml
        return ResponseEntity.badRequest().body("Error: You must send XML content (Content-Type: application/xml)");
    }

    /**
     * Handler multipar and missing request part exections
     * Meneja excepciones de multipart y partes de peticion faltantes
     * 
     * @return error message for file isses / mensaje de errores para problemas con el archivo
     */
    @ExceptionHandler({ MultipartException.class, MissingServletRequestPartException.class })
    public ResponseEntity<String> handlerMultiparError() {
        // Return error when file is missing or request is wrong
        // Retorna error cuando falta el archivo o la peticion es incorrecta
        return ResponseEntity.badRequest()
                .body("Error: The file has not been sent or the request is not of type 'form-data'");
    }
}

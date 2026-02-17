package com.example.formatter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.formatter.service.IParseService;

/**
 * Controller to handle XML to JSON conversion requests.
 * Controlador para manejar las peticiones de conversión de XML a JSON.
 */
@RestController
@RequestMapping("/api/parse")
public class ParseController {

    // Service dependency for logic execution
    // Dependencia del servicio para ejecutar la logica
    private final IParseService parseService;

    /**
     * Constructor for dependency injection
     * Constructor para la inyección de dependencias
     */
    public ParseController(IParseService parseService){
        this.parseService = parseService;
    }

    /**
     * Endpoint to convert an uploaded XML file to JSON
     * Punto de entrada para convertir un archivo XML subido a JSON
     * * @param file The XML file sent via multipart request / El archivo XML enviado por peticion multipart
     * @return ResponseEntity with JSON result or error message / Resultado en JSON o mensaje de error
     */
    @PostMapping("/xml-json-file")
    public ResponseEntity<Object> convert(@RequestParam("file") MultipartFile file) {
        try {
            // Call service to process the file / Llamada al servicio para procesar el archivo
            Object result = parseService.convertXMLFileToJSON(file);

            // Return 200 OK with the result / Retorna 200 OK con el resultado
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            // Return 400 Bad Request if something fails / Retorna 400 Bad Request si algo falla
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Endpoint to convert raw XML text string to JSON
     * Punto de entrada para convertir una cadena de texto XML puro a JSON
     * * @param xmlContent The XML string from the request body / El texto XML desde el cuerpo de la petición
     * @return ResponseEntity with JSON result or error message / Resultado en JSON o mensaje de error
     */
    @PostMapping(value="/xml-json-raw", consumes = "application/xml")
    public ResponseEntity<Object> convertRaw(@RequestBody String xmlContent) {
        try {
            // Process the raw string content / Procesa el contenido de la cadena de texto
            Object result = parseService.convertXMLRawToJSON(xmlContent);

            // Return successful response / Retorna respuesta exitosa
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            // Handle parsing or logic errors / Maneja errores de parseo o logica
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
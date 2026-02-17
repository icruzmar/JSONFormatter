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
 */
@RestController
@RequestMapping("/api/parse")
public class ParseController {

    // Service dependency for logic execution
    private final IParseService parseService;

    /**
     * Constructor for dependency injection
     */
    public ParseController(IParseService parseService){
        this.parseService = parseService;
    }

    /**
     * Endpoint to convert an uploaded XML file to JSON
     * @param file The XML file sent via multipart request
     * @return ResponseEntity with JSON result or error message
     */
    @PostMapping("/xml-json-file")
    public ResponseEntity<Object> convert(@RequestParam("file") MultipartFile file) {
        try {
            // Call service to process the file
            Object result = parseService.convertXMLFileToJSON(file);

            // Return 200 OK with the result
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            // Return 400 Bad Request if something fails
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Endpoint to convert raw XML text string to JSON
     * @param xmlContent The XML string from the request body
     * @return ResponseEntity with JSON result or error message
     */
    @PostMapping(value="/xml-json-raw", consumes = "application/xml")
    public ResponseEntity<Object> convertRaw(@RequestBody String xmlContent) {
        try {
            // Process the raw string content
            Object result = parseService.convertXMLRawToJSON(xmlContent);

            // Return successful response
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            // Handle parsing or logic errors
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
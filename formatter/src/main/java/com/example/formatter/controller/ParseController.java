package com.example.formatter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.formatter.service.IParseService;

@RestController
@RequestMapping("/api/parse")
public class ParseController {

    private final IParseService parseService;

    public ParseController(IParseService parseService){
        this.parseService = parseService;
    }

    @PostMapping("/xml-json-file")
    public ResponseEntity<Object> convert(@RequestParam("file") MultipartFile file) {
        try {
            Object result = parseService.convertXMLFileToJSON(file);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping(value="/xml-json-raw", consumes = "application/xml")
    public ResponseEntity<Object> convertRaw(@RequestBody String xmlContent) {
        try {
            Object result = parseService.convertXMLRawToJSON(xmlContent);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}

package com.example.formatter.service.impl;

import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.formatter.service.IParseService;

/**
 * Service implementation for parsing and converting xml data
 */
@Service
public class ServiceImpl implements IParseService {

    /**
     * Converts an uploaded xml file into a json compatible object
     */
    @Override
    public Object convertXMLFileToJSON(MultipartFile file) throws Exception {
        // Convert file bytes to a string using utf8 encoding
        String xml = new String(file.getBytes(), StandardCharsets.UTF_8);
        
        // Reuse the raw string conversion logic
        return convertXMLRawToJSON(xml);
    }

    /**
     * Converts a raw xml string into a json map
     */
    @Override
    public Object convertXMLRawToJSON(String xmlContent) throws Exception {
        // Parse the xml string into a jsonobject using the org json library
        JSONObject json = XML.toJSONObject(xmlContent);
        
        // Convert the jsonobject to a java map for spring to handle it as json
        return json.toMap();
    }
    
}
package com.example.formatter.service.impl;

import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.formatter.service.IParseService;

/**
 * Service implementation for parsing and converting xml data
 * Implementacion del servicio para analizar y convertir datos xml
 */
@Service
public class ServiceImpl implements IParseService {

    /**
     * Converts an uploaded xml file into a json compatible object
     * Convierte un archivo xml subido en un objeto compatible con json
     */
    @Override
    public Object convertXMLFileToJSON(MultipartFile file) throws Exception {
        // Convert file bytes to a string using utf8 encoding
        // Convierte los bytes del archivo a una cadena de texto usando codificacion utf8
        String xml = new String(file.getBytes(), StandardCharsets.UTF_8);
        
        // Reuse the raw string conversion logic
        // Reutiliza la logica de conversion de cadena de texto pura
        return convertXMLRawToJSON(xml);
    }

    /**
     * Converts a raw xml string into a json map
     * Convierte una cadena de texto xml puro en un mapa json
     */
    @Override
    public Object convertXMLRawToJSON(String xmlContent) throws Exception {
        // Parse the xml string into a jsonobject using the org json library
        // Analiza la cadena xml y la convierte en un jsonobject usando la libreria org json
        JSONObject json = XML.toJSONObject(xmlContent);
        
        // Convert the jsonobject to a java map for spring to handle it as json
        // Convierte el jsonobject a un mapa de java para que spring lo maneje como json
        return json.toMap();
    }
    
}
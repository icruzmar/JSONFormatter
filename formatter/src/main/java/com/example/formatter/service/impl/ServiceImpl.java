package com.example.formatter.service.impl;

import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.formatter.service.IParseService;

@Service
public class ServiceImpl implements IParseService {

    @Override
    public Object convertXMLFileToJSON(MultipartFile file) throws Exception {
        String xml = new String(file.getBytes(), StandardCharsets.UTF_8);
        return convertXMLRawToJSON(xml);
    }

    @Override
    public Object convertXMLRawToJSON(String xmlContent) throws Exception {
       JSONObject json = XML.toJSONObject(xmlContent);
       return json.toMap();
    }
    
}

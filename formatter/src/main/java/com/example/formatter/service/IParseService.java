package com.example.formatter.service;

import org.springframework.web.multipart.MultipartFile;

public interface IParseService {
    Object convertXMLFileToJSON (MultipartFile file) throws Exception;
    
    Object convertXMLRawToJSON (String xmlContent) throws Exception;
}

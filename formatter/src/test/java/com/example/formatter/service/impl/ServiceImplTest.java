package com.example.formatter.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

class ServiceImplTest {

    private ServiceImpl service;

    @BeforeEach
    void setup(){
        service = new ServiceImpl();
    }

    @Test
    void convert_string_xml_to_json() throws Exception{
        String xml = "<user><name>Juan</name></user>";

        Object result = service.convertXMLRawToJSON(xml);

        assertNotNull(result);
        assertTrue(result instanceof Map);

        Map<?,?> map = (Map<?,?>) result;
        assertTrue(map.containsKey("user"));
    }


    @Test
    void convert_file_xml_to_json() throws Exception{
        String contxml = "<product><id>10</id></product>";
        MockMultipartFile file = new MockMultipartFile("file", "data.xml","text/xml",contxml.getBytes());

        Object result = service.convertXMLFileToJSON(file);

        Map<?,?> map = (Map<?,?>)result;
        assertTrue(map.containsKey("product"));
    }
}

package com.example.formatter.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.example.formatter.service.IParseService;

@WebMvcTest(ParseController.class)
class ParseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IParseService parseService;

    // Test de File
    @Test
    void convert_file_valid() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.xml", "text/xml", "<root></root>".getBytes());

        when(parseService.convertXMLFileToJSON(any())).thenReturn("JSON OK");

        mockMvc.perform(multipart("/api/parse/xml-json-file").file(file))
                .andExpect(status().isOk())
                .andExpect(content().string("JSON OK"));
    }

    // Test de Raw
    @Test
    void convert_raw_valid() throws Exception{
        String xmlRaw = "<root></root>";

        when(parseService.convertXMLRawToJSON(xmlRaw)).thenReturn("JSON RAW OK");

        mockMvc.perform(post("/api/parse/xml-json-raw")
                .contentType("application/xml")
                .content(xmlRaw))
                .andExpect(status().isOk())
                .andExpect(content().string("JSON RAW OK"));
    }
}

package com.example.formatter.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.formatter.service.IParseService;

@WebMvcTest(ParseController.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IParseService parseService;

    @Test
    void test_handler_media__type_error() throws Exception {

        mockMvc.perform(post("/api/parse/xml-json-raw")
                .contentType("text/plain")
                .content("not xml"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: You must send XML content (Content-Type: application/xml)"));
    }

    @Test
    void test_handler_multipart_error() throws Exception {
        mockMvc.perform(post("/api/parse/xml-json-file"))
                .andExpect(status().isBadRequest())
                .andExpect(
                        content().string("Error: The file has not been sent or the request is not of type 'form-data'"));
    }

}

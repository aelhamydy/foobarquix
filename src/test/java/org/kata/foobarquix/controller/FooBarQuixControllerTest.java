package org.kata.foobarquix.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kata.foobarquix.service.FooBarQuixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(FooBarQuixController.class)
class FooBarQuixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FooBarQuixService fooBarQuixService;

    @Test
    void testControllerReturnsExpectedResult() throws Exception {
        when(fooBarQuixService.transform(3)).thenReturn("FOOFOO");
        when(fooBarQuixService.transform(5)).thenReturn("BARBAR");


        mockMvc.perform(get("/api/convert/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("FOOFOO"));

        mockMvc.perform(get("/api/convert/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("BARBAR"));
    }
}

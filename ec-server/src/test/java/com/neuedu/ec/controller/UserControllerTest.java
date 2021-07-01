package com.neuedu.ec.controller;

import com.neuedu.ec.ServerApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServerApp.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void wxlogin() throws Exception {

        String js_code = "011goS000QiAwL1UWO300YW4AP1goS0V";

        System.out.println("js_code:"+js_code);

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/wxlogin")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .param("js_code", js_code)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse();


        
    }
}
package com.bfs.onboard.controller;

import com.bfs.onboard.domain.response.RegistrationResponse;
import com.bfs.onboard.service.RegistrationService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest(controllers = RegisterController.class)
public class RegisterControllerTest {

    @MockBean
    private RegistrationService service;

    @Autowired
    private MockMvc mockMvc;

    //registerStart( String email, String token, String username, String password)
    @Test
    void registerStart() throws Exception{
        String email = "kakatcy@gmail.com";
        String token = "d81500c3-1ebe-4468-81c7-3c37cc24204c";
        String username = "mock";
        String password = "mock";
        RegistrationResponse response = new RegistrationResponse();
        response.setEmail(email);
        response.setToken(token);
        response.setUsername(username);
        response.setPassword(password);
        response.setSuccess(true);

        Mockito.when(service.register(email,token,username,password)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/login/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email",email)
                        .param("token",token)
                        .param("username", username)
                        .param("password",password))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("{'email': 'kakatcy@gmail.com','token': 'd81500c3-1ebe-4468-81c7-3c37cc24204c', 'username': 'mock', 'password': 'mock','success':true}"));
    }

}


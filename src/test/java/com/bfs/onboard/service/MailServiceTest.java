package com.bfs.onboard.service;

import com.bfs.onboard.mail.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailServiceTest {
    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private MailService service;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "registrationUrl", "http://localhost:4200/login/register");
    }

    //sendRegisterToken(String email, String token)
    @Test
    void sendRegisterToken(){
        String email = "kakatcy@gmail.com";
        String token = "123";
        String expected = "http://localhost:4200/login/register?email=kakatcy@gmail.com&token=123";
        String actualLink = service.sendRegisterToken(email, token);
        assertEquals(expected, actualLink);
    }


}

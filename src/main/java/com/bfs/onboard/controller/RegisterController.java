package com.bfs.onboard.controller;

import com.bfs.onboard.response.RegistrationResponse;
import com.bfs.onboard.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class RegisterController {

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerStart(
            String email, String token, String username, String password) {

        RegistrationResponse response;
        if (email == null || "".equals(email) || token == null || "".equals(token) ||
                username == null || "".equals(username) || password == null || "".equals(password))
            response = new RegistrationResponse("One of input is empty!");
        else
            response = registrationService.register(email, token, username, password);

        return new ResponseEntity<>(response, response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}

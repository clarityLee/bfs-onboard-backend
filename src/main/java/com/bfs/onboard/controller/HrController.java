package com.bfs.onboard.controller;

import com.bfs.onboard.constant.Constant;
import com.bfs.onboard.security.util.JwtUtil;
import com.bfs.onboard.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/hr")
public class HrController {

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/sendRegisterToken")
    public ResponseEntity<Void> generateToken(
            HttpServletRequest httpServletRequest,
            @RequestParam String email) {

        String username = "hr";
        // TODO: get username from cookie
//        String username = JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY);
        if (username == null || username.isEmpty()) {
            System.out.println("username is " + (username == null ? "null" : "empty"));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean result = registrationService.sendRegisterToken(username, email);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}

package com.bfs.onboard.controller;

import com.bfs.onboard.constant.Constant;
import com.bfs.onboard.domain.requestDto.OnBoardingDto;
import com.bfs.onboard.security.util.JwtUtil;
import com.bfs.onboard.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OnBoardingController {

    private OnBoardingService onBoardingService;

    @Autowired
    public void setOnBoardingService(OnBoardingService onBoardingService) {
        this.onBoardingService = onBoardingService;
    }

    @PostMapping("/on-boarding")
    public ResponseEntity<String> onBoardingSubmit(HttpServletRequest httpServletRequest,
                                                   @RequestBody final OnBoardingDto form) {

        // TODO: modify requestDto to get

        String username = JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY);

        // TODO: parse permission

        boolean result = onBoardingService.save(username, form);

        System.out.println("received");
        if (result)
            return new ResponseEntity<>("success", HttpStatus.OK);
        else
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
    }

}

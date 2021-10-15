package com.bfs.onboard.controller;

import com.bfs.onboard.domain.requestDto.OnBoardingDto;
import com.bfs.onboard.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class OnBoardingController {

    private OnBoardingService onBoardingService;

    @Autowired
    public void setOnBoardingService(OnBoardingService onBoardingService) {
        this.onBoardingService = onBoardingService;
    }

    @PostMapping("/on-boarding")
    public ResponseEntity<String> onBoardingSubmit(HttpServletRequest httpServletRequest,
                                                   @RequestBody final OnBoardingDto form) {

        // for dev - test
        System.out.println("received");
        return new ResponseEntity<>("OK", HttpStatus.OK);

        //TODO: change back to normal flow:
//        boolean result = onBoardingService.save(form);
//
//        if (result)
//            return new ResponseEntity<>("OK", HttpStatus.OK);
//        else
//            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
    }

}

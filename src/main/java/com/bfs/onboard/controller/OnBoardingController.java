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
import java.util.List;

@RestController
public class OnBoardingController {

    private OnBoardingService onBoardingService;

    @Autowired
    public void setOnBoardingService(OnBoardingService onBoardingService) {
        this.onBoardingService = onBoardingService;
    }

    @PostMapping("/on-boarding")
    public ResponseEntity<List<String>> onBoardingSubmit(
            @RequestBody final OnBoardingDto form) {
        List<String> errs = form.errMessages();

        if (!errs.isEmpty())
            return new ResponseEntity<>(errs, HttpStatus.BAD_REQUEST);

        boolean result = onBoardingService.save(form);
        return new ResponseEntity<>(errs, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}

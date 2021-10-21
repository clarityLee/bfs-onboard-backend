package com.bfs.onboard.exception.handler;

import com.bfs.onboard.exception.MyS3FileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyS3FileNotFoundExceptionHandler {

    @ExceptionHandler(value={MyS3FileNotFoundException.class})
    public ResponseEntity<String> handlerUserNotFound(MyS3FileNotFoundException e){
        System.out.println("If MyS3FileNotFoundExceptionHandler throws, it will be intercepted by this handler");
        return new ResponseEntity<>("Your request file is not found: " + e.toString(), HttpStatus.NOT_FOUND);
    }

}

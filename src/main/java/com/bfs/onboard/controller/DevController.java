package com.bfs.onboard.controller;

import com.bfs.onboard.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DevController {

    private RegistrationService registrationService;

    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/sendTestMail")
    public String getTestMail() {
        return "TestSendRegisterToken";
    }

    @PostMapping(path = "/sendTestMail", produces = MediaType.TEXT_PLAIN_VALUE)
    public String postTestMail(@RequestParam String email, Model model) {

        // this is HR id
        Integer userId = 2;

        String link = registrationService.sendRegisterToken(userId, email);
        model.addAttribute("link", link);
        return "TestSendRegisterToken";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login/register")
    public String registerPage() {
        return "registration";
    }

    @GetMapping("/test/upload")
    @ResponseBody
    public String testUpload() {


        System.out.println("nothing happened.");
        return "request complete";
    }
}

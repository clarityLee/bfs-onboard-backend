package com.bfs.onboard.controller;

import com.bfs.onboard.constant.AppWorkStatus;
import com.bfs.onboard.constant.Constant;
import com.bfs.onboard.domain.PersonalDocument;
import com.bfs.onboard.domain.response.OnboardingAppDto;
import com.bfs.onboard.security.util.JwtUtil;
import com.bfs.onboard.service.DocumentService;
import com.bfs.onboard.service.OnBoardingService;
import com.bfs.onboard.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/hr")
public class HrController {

    private DocumentService documentService;
    private RegistrationService registrationService;
    private OnBoardingService onBoardingService;

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    @Autowired
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @Autowired
    public void setOnBoardingService(OnBoardingService onBoardingService) {
        this.onBoardingService = onBoardingService;
    }

    @PostMapping("/sendRegisterToken")
    public ResponseEntity<Void> generateToken(
            HttpServletRequest httpServletRequest,
            @RequestParam String email) {

        String username = JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY);
        if (username == null || username.isEmpty()) {
            System.out.println("username is " + (username == null ? "null" : "empty"));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean result = registrationService.sendRegisterToken(username, email);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    // --- hiring ---
    @GetMapping("/onboardingAppList")
    public ResponseEntity<List<OnboardingAppDto>> onboardingApps() {
        return new ResponseEntity<>(onBoardingService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/comment/docs/{id}")
    public ResponseEntity<Void> commentPersonalDocs(
            @PathVariable(name = "id") Integer personalDocumentId,
            @RequestParam String comment) {

        documentService.saveComment(personalDocumentId, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/onboarding-decision")
    public ResponseEntity<Void> onboardingDecision(
            @RequestParam Integer applicationWorkFlowId,
            @RequestParam Boolean accept,
            @RequestParam List<String> rejectReason) {


        onBoardingService.hrDecision(applicationWorkFlowId, accept, rejectReason);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.bfs.onboard.controller;

import com.bfs.onboard.constant.Constant;
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

    /**
     * Returns the list for Application Review
     * @return
     */
    @GetMapping("/onboardingAppList")
    public ResponseEntity<List<OnboardingAppDto>> onboardingApps() {
        return new ResponseEntity<>(onBoardingService.getAll(), HttpStatus.OK);
    }

    /**
     * Save a comment to a personal document in the Details window of a user
     * @param personalDocumentId
     * @param comment
     * @return
     */
    @PostMapping("/comment/docs")
    public ResponseEntity<Void> commentPersonalDocs(
            @RequestParam Integer personalDocumentId,
            @RequestParam String comment) {

        documentService.saveComment(personalDocumentId, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Save a Application level comment into applicationWorkFlow
     * @param applicationWorkFlowId
     * @param comment
     * @return
     */
    @PostMapping("/comment/application")
    public ResponseEntity<Void> commentApplication(
            @RequestParam Integer applicationWorkFlowId,
            @RequestParam String comment) {
        onBoardingService.saveComment(applicationWorkFlowId, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Approve a hiring application
     * @param applicationWorkFlowId
     * @return
     */
    @PostMapping("/hiring/approve")
    public ResponseEntity<Void> hiringApprove(
            @RequestParam Integer applicationWorkFlowId) {
        return new ResponseEntity<>(onBoardingService.hiringApprove(applicationWorkFlowId) ?
                HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    /**
     * Reject a hiring application
     * @param applicationWorkFlowId
     * @param rejectReason
     * @return
     */
    @PostMapping("/hiring/reject")
    public ResponseEntity<Void> hiringReject(
            @RequestParam Integer applicationWorkFlowId,
            @RequestParam List<String> rejectReason) {
        return new ResponseEntity<>(onBoardingService.hiringReject(applicationWorkFlowId, rejectReason) ?
                HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}

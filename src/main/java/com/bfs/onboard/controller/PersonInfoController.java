package com.bfs.onboard.controller;

import com.bfs.onboard.constant.Constant;
import com.bfs.onboard.domain.requestDto.AddressDto;
import com.bfs.onboard.domain.requestDto.EditEmploymentDto;
import com.bfs.onboard.domain.requestDto.EditPersonDto;
import com.bfs.onboard.domain.response.EmployeeResponse;
import com.bfs.onboard.security.util.JwtUtil;
import com.bfs.onboard.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class PersonInfoController {

    private InfoService infoService;
    @Autowired
    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }

    /**Get all the details of a user for Details window
     * @return
     */
    @GetMapping("/details/myInfo")
    public ResponseEntity<EmployeeResponse> personDetailsByUser(
            HttpServletRequest httpServletRequest) {

        String username = JwtUtil.getSubject(httpServletRequest, Constant.JWT_TOKEN_COOKIE_NAME, Constant.SIGNING_KEY);
        if (username == null || username.isEmpty()) {
            System.out.println("username is " + (username == null ? "null" : "empty"));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                infoService.getEmployeeDetailsByUser(username), HttpStatus.OK);
    }

    /**Get all the details of a user for Details window
     * @param employeeId
     * @return
     */
    @GetMapping("/details/employee/{employeeId}")
    public ResponseEntity<EmployeeResponse> personDetailsByEmployee(
            @PathVariable(name = "employeeId") Integer employeeId) {
        return new ResponseEntity<>(
                infoService.getEmployeeDetailsByEmployeeId(employeeId), HttpStatus.OK);
    }

    // edit Name section
    @PostMapping("/employee/edit-person")
    public ResponseEntity<Void> editPerson(
            @RequestBody final EditPersonDto editPersonDto) {

        infoService.editPerson(editPersonDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // edit Employment section
    @PostMapping("/employee/edit-employee")
    public ResponseEntity<Void> editEmployee(
            @RequestBody EditEmploymentDto editEmploymentDto) {
        infoService.editEmployment(editEmploymentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // edit contactInfo section
    @PostMapping("/employee/edit-contact")
    public ResponseEntity<Void> editContact(
            @RequestParam Integer employeeId,
            @RequestParam String personalEmail,
            @RequestParam String workEmail,
            @RequestParam String cellphone,
            @RequestParam String workPhone) {

        infoService.editEContact(employeeId, personalEmail,
                workEmail, cellphone, workPhone);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // edit any existing address
    @PostMapping("/employee/edit-address")
    public ResponseEntity<Void> editAddress(
            @RequestBody AddressDto addressDto) {

        infoService.editAddress(addressDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

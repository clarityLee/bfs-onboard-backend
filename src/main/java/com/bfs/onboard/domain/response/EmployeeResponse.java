package com.bfs.onboard.domain.response;

import com.bfs.onboard.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeResponse {
    private User user;
    private Person person;
    private Employee employee;
    private ApplicationWorkFlow applicationWorkFlow;
    private VisaStatus visaStatus;
    private Contact reference;
    private List<Contact> emergencyList;
    private List<PersonalDocument> personalDocuments;
    private List<Address> addresses;
}

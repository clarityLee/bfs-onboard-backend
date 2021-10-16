package com.bfs.onboard.domain.requestDto;

import com.bfs.onboard.domain.Contact;
import com.bfs.onboard.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ContactDto {

    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private AddressDto address;
    private String email;
    private String relationship;

    public Person toPerson() {
        Person p = new Person();
        p.setFirstName(firstName);
        p.setMiddleName(middleName);
        p.setLastName(lastName);
        p.setCellPhone(phone);
        p.setEmail(email);
        return p;
    }

    public Contact toRefContact(Integer personId) {
        Contact c = new Contact();
        c.setPersonID(personId);
        c.setRelationship(relationship);
        c.setIsReference(true);
        return c;
    }

    public Contact toEmergencyContact(Integer personId) {
        Contact c = new Contact();
        c.setPersonID(personId);
        c.setRelationship(relationship);
        c.setIsEmergency(true);
        return c;
    }
}

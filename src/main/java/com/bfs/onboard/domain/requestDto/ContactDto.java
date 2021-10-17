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

    public Contact toRefContact(Person person, Integer ownerId) {
        Contact c = new Contact();
        c.setPerson(person);
        c.setRelationship(relationship);
        c.setIsReference(true);
        c.setOwnerId(ownerId);
        return c;
    }

    public Contact toEmergencyContact(Person personId, Integer ownerId) {
        Contact c = new Contact();
        c.setPerson(personId);
        c.setRelationship(relationship);
        c.setIsEmergency(true);
        c.setOwnerId(ownerId);
        return c;
    }
}

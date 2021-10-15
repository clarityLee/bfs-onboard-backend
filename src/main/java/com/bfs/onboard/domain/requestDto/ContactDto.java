package com.bfs.onboard.domain.requestDto;

import com.bfs.onboard.domain.Address;
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
    private String Phone;
    private Address address;
    private String email;
    private String relationship;

}

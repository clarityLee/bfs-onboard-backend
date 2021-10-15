package com.bfs.onboard.domain.requestDto;

import com.bfs.onboard.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDto {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipcode;
    private String stateName;
    private String stateAbbr;

    public Address toAddress(Integer personId) {
        Address a = new Address();
        a.setPersonID(personId);
        a.setAddressLine1(addressLine1);
        a.setAddressLine2(addressLine2);
        a.setCity(city);
        a.setStateAbbr(stateAbbr);
        a.setStateName(stateName);
        a.setZipcode(zipcode);
        return a;
    }

}

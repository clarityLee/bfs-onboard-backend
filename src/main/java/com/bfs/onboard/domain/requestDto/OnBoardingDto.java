package com.bfs.onboard.domain.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OnBoardingDto {

    // * required, hidden from user;
    private String username;
    private String email;

    // * required
    private String firstName;

    // * required
    private String lastName;

    private String middleName;
    private String preferredName;

    // path to uploaded personal picture
    private String avatar;

    // * required
    private AddressDto currentAddress;

    // * required
    private String ceilPhone;

    private String workPhone;
    private String car;

    // * required
    private String SSN;

    // * required
    private LocalDate dateOfBirth;

    // * required: Male, Female, I don't want to answer
    private String Gender;

    // * required
    private Boolean resident;

    private Boolean citizen;
    private Boolean greenCard;

    // H1-B, L2, F1(CPT/OPT), H4, Other
    private String workAuthorization;

    // the path for uploaded work Authorization Document path
    private String workAuthUploadPath;

    // * required
    private Boolean hasDriveLicense;

    private String driveLicenseNumber;
    private String driveLicenseExpireDate;

    // the path for uploaded Driving License Picture Path
    private String driveLicensePath;

    private ContactDto reference;

    // * required at least one record
    private List<ContactDto> emergencyList;

}

package com.bfs.onboard.domain.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;

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
    private String ssn;

    // * required
    private LocalDate dateOfBirth;

    // * required: Male, Female, I don't want to answer
    private String gender;

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
    private LocalDate driveLicenseExpireDate;

    // the path for uploaded Driving License Picture Path
    private String driveLicensePath;

    private ContactDto reference;

    // * required at least one record
    private List<ContactDto> emergencyList;

    // This is only for dev
    public List<String> errMessages() {
        List<String> e = new ArrayList<>();
        if (!StringUtils.hasLength(username))
            e.add("username err");
        if (!StringUtils.hasLength(email))
            e.add("email err");
        if (!StringUtils.hasLength(firstName))
            e.add("firstName err");
        if (!StringUtils.hasLength(lastName))
            e.add("lastName err");
        if (!StringUtils.hasLength(ceilPhone))
            e.add("ceilPhone err");
        if (!StringUtils.hasLength(ssn))
            e.add("SSN err");
        if (!StringUtils.hasLength(gender))
            e.add("Gender err");
        if (resident == null)
            e.add("resident err");
        if (citizen == null)
            e.add("citizen err");
        if (greenCard == null)
            e.add("greenCard err");

        if (resident != null && !resident) {
            if (StringUtils.hasLength(workAuthorization)) {
                List<String> accepts = Arrays.asList("H1-B", "L2", "F1(CPT/OPT)", "H4", "Other");
                if (!accepts.contains(workAuthorization))
                    e.add("wrong workAuthorization. workAuthorization must be in: H1-B, L2, F1(CPT/OPT), H4, Other" );
                if (!StringUtils.hasLength(workAuthUploadPath))
                    e.add("Missing workAuthUploadPath.");
            } else {
                e.add("missing workAuthorization because your are not a citizen.");
            }
        }

        if (hasDriveLicense == null)
            e.add("hasDriveLicense err");
        if (hasDriveLicense != null && hasDriveLicense) {
            if (driveLicenseExpireDate == null)
                e.add("missing driveLicenseExpireDate");
            if (!StringUtils.hasLength(driveLicensePath))
                e.add("missing drive license upload path");
        }

        if (currentAddress == null)
            e.add("currentAddress err");
        else
            checkAddress(e, currentAddress, "user");


        if (emergencyList == null || emergencyList.isEmpty())
            e.add("missing emergencyList");
        else {
            for (int i = 0 ; i < emergencyList.size() ; ++i) {
                ContactDto c = emergencyList.get(i);
                checkConatct(e, c, "emergency contact (" + i + ")");
                checkAddress(e, c.getAddress(), "emergency contact (" + i + ")");
            }
        }

        if (reference != null) {
            checkConatct(e, reference, "reference contact");
            checkAddress(e, reference.getAddress(), "reference contact");
        }

        return e;
    }

    private void checkConatct(List<String> e, ContactDto c, String target) {
        if (!StringUtils.hasLength(c.getFirstName()))
            e.add(target + "'s firstName is missing");
        if (!StringUtils.hasLength(c.getLastName()))
            e.add(target + "'s lastName is missing");
        if (!StringUtils.hasLength(c.getPhone()))
            e.add(target + "'s phone is missing");
    }

    private void checkAddress(List<String> err, AddressDto a, String target) {
        if (!StringUtils.hasLength(a.getAddressLine1()))
            err.add(target + "'s address1 is missing");
        if (!StringUtils.hasLength(a.getCity()))
            err.add(target + "'s city is missing");
        if (!StringUtils.hasLength(a.getZipcode()))
            err.add(target + "'s zipcode is missing");
        if (!StringUtils.hasLength(a.getStateName()))
            err.add(target + "'s stateName is missing");
    }
}

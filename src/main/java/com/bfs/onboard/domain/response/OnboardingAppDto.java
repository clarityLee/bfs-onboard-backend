package com.bfs.onboard.domain.response;

import com.bfs.onboard.constant.AppWorkStatus;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@NoArgsConstructor
@Getter
public class OnboardingAppDto {

    private final Integer employeeId;

    // person.id
    private final Integer personId;

    // person.firstname
    private final String firstname;

    // person.lastname
    private final String lastname;

    // visaStatus.visaType
    private final String visaType;

    // employee.visastartdate
    private final String visaStartDate;

    // employee.visaenddate
    private final String visaEndDate;

    private final Integer applicationWorkFlowId;

    // appicationWorkFlow.status
    private final String status;

    public OnboardingAppDto(
            Integer employeeId,
            Integer personId,
            Integer applicationWorkFlowId,
            String firstname,
            String lastname,
            String visaType,
            LocalDate visaStartDate,
            LocalDate visaEndDate,
            Integer appStatus
    ) {
        this.employeeId = employeeId;
        this.personId = personId;
        this.applicationWorkFlowId = applicationWorkFlowId;
        this.firstname = firstname;
        this.lastname = lastname;

        if (StringUtils.hasLength(visaType))
            this.visaType = visaType;
        else
            this.visaType = "";

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        if (visaStartDate != null)
            this.visaStartDate = visaStartDate.format(formatters);
        else
            this.visaStartDate = "";

        if (visaEndDate != null)
            this.visaEndDate = visaEndDate.format(formatters);
        else
            this.visaEndDate = "";

        this.status = AppWorkStatus.statusToString(appStatus);
    }

}

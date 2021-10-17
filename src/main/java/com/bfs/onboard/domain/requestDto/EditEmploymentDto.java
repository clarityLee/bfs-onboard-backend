package com.bfs.onboard.domain.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class EditEmploymentDto {

    private Integer employeeId;

    private String workAuthorization;

    // work Authorization start date
    private LocalDate visaStartDate;

    // work Authorization end date
    private LocalDate visaEndDate;

    // Employment start date
    private LocalDate startDate;

    // Employment end date
    private LocalDate endDate;

    private String title;
}

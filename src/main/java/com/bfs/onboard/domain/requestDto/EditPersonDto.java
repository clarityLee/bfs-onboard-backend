package com.bfs.onboard.domain.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class EditPersonDto {

    private Integer personId;
    private String firstName;
    private String lastName;
    private String preferredName;
    private LocalDate dateOfBirth;
    private String gender;
    private String ssn;
}

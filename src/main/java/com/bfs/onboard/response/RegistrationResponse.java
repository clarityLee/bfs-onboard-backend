package com.bfs.onboard.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationResponse {
    private Boolean success = false;
    private String errorMessage;
    private String email;
    private String token;
    private String username;
    private String password;

    public RegistrationResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

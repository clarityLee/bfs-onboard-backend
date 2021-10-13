package com.bfs.onboard.dao;

import com.bfs.onboard.domain.RegistrationToken;

import java.time.LocalDateTime;

public interface RegistrationTokenDao {
    void save(RegistrationToken registrationToken);
    boolean validate(String email, String token, LocalDateTime now);
    RegistrationToken findByToken(String token);
}

package com.bfs.onboard.dao;

import com.bfs.onboard.domain.response.OnboardingAppDto;

import java.util.List;

public interface OnboardingDao {
    List<OnboardingAppDto> getAll();
}

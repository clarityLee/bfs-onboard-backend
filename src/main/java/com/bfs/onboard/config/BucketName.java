package com.bfs.onboard.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Getter
public enum BucketName {
    AVATAR("spring-amazon-storage");
    private final String bucketName;
}

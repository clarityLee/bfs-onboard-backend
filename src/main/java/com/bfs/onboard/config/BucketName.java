package com.bfs.onboard.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    AVATAR("bfs-avatar");
    private final String bucketName;
}

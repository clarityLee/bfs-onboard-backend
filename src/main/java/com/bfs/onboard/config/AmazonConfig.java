package com.bfs.onboard.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:GmailAmazonS3Rds.properties")
public class AmazonConfig {

    @Value("${s3.access.key}")
    private String accessKey;

    @Value("${s3.secret.key}")
    private String secretKey;

    public final static String AVATAR = "bfs-avatar";
    public final static String DRIVING_LICENSE = "bfs-driving-license";
    public final static String WORK_AUTHORICATION = "bfs-work-authorization";
    public final static String ONBIARDING_FILES = "bfs-onboard-files";

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("us-east-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Bean
    public Set<String> s3Buckets() {
        Set<String> set = new HashSet<>();
        set.add(AVATAR);
        set.add(DRIVING_LICENSE);
        set.add(WORK_AUTHORICATION);
        set.add(ONBIARDING_FILES);
        return set;
    }
}

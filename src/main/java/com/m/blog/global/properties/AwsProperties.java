package com.m.blog.global.properties;

import lombok.Data;
import lombok.Getter;
import org.apache.http.auth.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties("cloud")
@Data
public class AwsProperties {
//    @Value("${cloud.aws.credentials.access-key:#{null}}") private String accessKey;
//    @Value("${cloud.aws.credentials.secret-key:#{null}}") private String secretKey;
//    @Value("${cloud.aws.region.static:#{null}}") private String region;
//    @Value("${cloud.aws.s3.bucket:#{null}}") private String bucket;

    private Credentials credentials;
    private S3 s3;
    private String region;
    @Data
    public static class Credentials{
        private String accessKey;
        private String secretKey;
    }


    @Data
    public static class S3{
         private String bucket;
    }

}

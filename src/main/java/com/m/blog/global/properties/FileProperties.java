package com.m.blog.global.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class FileProperties {
    @Value("${file.directory:#{null}}") private String directoryName;
    @Value("${cloud.aws.credentials.access-key:#{null}}") private String accessKey;
    @Value("${cloud.aws.credentials.secret-key:#{null}}") private String secretKey;
    @Value("${cloud.aws.region.static:#{null}}") private String region;
    @Value("${cloud.aws.s3.bucket:#{null}}") private String bucket;
    @Value("${file.isLocal:#{null}}") private boolean isLocal;
}

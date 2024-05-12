package com.m.blog.aggregate.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.global.properties.AwsProperties;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@RequiredArgsConstructor
public class S3FileDownloadUtilImpl implements FileDownloadUtil{
    private final AmazonS3 amazonS3;
    private final AwsProperties awsProperties;

    @Override
    public InputStream get(File_ file){
        GetObjectRequest request =
                new GetObjectRequest(awsProperties.getS3().getBucket(), file.getFileId().getValue());

        S3Object s3Object = amazonS3.getObject(request);

        return s3Object.getObjectContent();
    }
}

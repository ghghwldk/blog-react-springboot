package com.m.blog.aggregate.file.infrastructure.file;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.m.blog.global.properties.AwsProperties;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class S3FileDeleteUtilExtended extends FileDeleteUtil{
    private final AmazonS3 amazonS3;
    private final AwsProperties awsProperties;

    @Override
    protected void delete(List<String> targets) {
        try {
            for(String target: targets){
                DeleteObjectRequest request =
                        new DeleteObjectRequest(awsProperties.getS3().getBucket(), target);

                amazonS3.deleteObject(request);
            }
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }
}

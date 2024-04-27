package com.m.blog.aggregate.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.m.blog.global.properties.AwsProperties;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class LocalFileDeleteUtilExtended extends FileDeleteUtil{

    @Override
    protected void delete(List<String> current){
        waitings.stream()
                .map(File::new)
                .collect(Collectors.toList())
                .forEach(FileUtils::deleteQuietly);
    }
}

package com.m.blog.aggregate.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.global.properties.AwsProperties;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LocalFileDeleteUtilExtended extends FileDeleteUtil{
    private final FileProperties fileProperties;

    @Override
    protected void delete(List<File_.FileId> fileIds){
        String directoryName = fileProperties.getDirectoryName();
        fileIds.stream()
                .map(fileId-> directoryName + "/" + fileId.getValue())
                .map(File::new)
                .collect(Collectors.toList())
                .forEach(FileUtils::deleteQuietly);
    }
}

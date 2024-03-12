package com.m.blog.domain.loadfiletoawscloud.adapter.file;

import com.m.blog.common.Adapter;
import com.m.blog.domain.loadfiletoawscloud.application.domain.File;
import com.m.blog.domain.loadfiletoawscloud.application.domain.FileUtils;
import com.m.blog.domain.loadfiletoawscloud.application.port.file.FilePort;
import com.m.blog.domain.loadfiletoawscloud.infrastructure.aws.AmazonClient;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class FileAdapter implements FilePort {
    private final AmazonClient amazonClient;

    @Override
    public void uploadFile(File file){
        amazonClient.uploadFileToBucket(file.getUuidAwsFile(), FileUtils.bytesToFile(file.getData(), file.getName()), String.valueOf(file.getFileNature()));
    }

    @Override
    public boolean fileExist(Optional<File> file){
        return amazonClient
                .fileExistInBucket(
                        file.get().getFileNature()+"/"+file.get().getUuidAwsFile()
                );
    }
    @Override
    public InputStream getInputStream(File file){
        return amazonClient.getFileInputStream(file.getUuidAwsFile(),String.valueOf(file.getFileNature()));
    }
}

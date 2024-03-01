package com.m.blog.domain.file.adapter.file;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.domain.file.application.port.file.FileUploadPort;
import com.m.blog.domain.file.infrastructure.file.FileUploadHelper;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Adapter
@RequiredArgsConstructor
public class FileUploadAdapter implements FileUploadPort {
    private final FileProperties fileProperties;
    private final FileUploadHelper fileUploadHelper;

    @Override
    public void upload(UploadFile uploadFile) throws IOException {
        if(fileProperties.isLocal()){
            fileUploadHelper.uploadOnLocal(uploadFile);
        }else{
            fileUploadHelper.uploadOnS3(uploadFile);
        }
    }
}

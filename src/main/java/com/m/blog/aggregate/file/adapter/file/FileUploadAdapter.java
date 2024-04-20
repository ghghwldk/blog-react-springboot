package com.m.blog.aggregate.file.adapter.file;

import com.m.blog.common.Adapter;
import com.m.blog.aggregate.file.application.domain.UploadedFile;
import com.m.blog.aggregate.file.application.port.file.FileUploadPort;
import com.m.blog.aggregate.file.infrastructure.file.FileUploadUtil;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Adapter
@RequiredArgsConstructor
public class FileUploadAdapter implements FileUploadPort {
    private final FileProperties fileProperties;
    private final FileUploadUtil fileUploadUtil;

    @Override
    public void upload(UploadedFile uploadedFile) throws IOException {
        if(fileProperties.isForLocal()){
            fileUploadUtil.uploadOnLocal(uploadedFile);
        }else{
            fileUploadUtil.uploadOnS3(uploadedFile);
        }
    }
}

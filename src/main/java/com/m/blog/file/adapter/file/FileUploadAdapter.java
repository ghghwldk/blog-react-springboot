package com.m.blog.file.adapter.file;

import com.m.blog.common.Adapter;
import com.m.blog.file.application.domain.UploadedFile;
import com.m.blog.file.application.port.file.FileUploadPort;
import com.m.blog.file.infrastructure.file.FileUploadUtil;
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

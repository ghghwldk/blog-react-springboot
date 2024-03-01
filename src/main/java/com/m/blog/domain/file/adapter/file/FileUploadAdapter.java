package com.m.blog.domain.file.adapter.file;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.domain.file.application.port.file.FileUploadPort;
import com.m.blog.domain.file.infrastructure.file.FileUploadHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Adapter
@RequiredArgsConstructor
public class FileUploadAdapter implements FileUploadPort {
    @Value("${cloud.aws.s3.bucket:#{null}}") private String bucket;
    @Value("${file.isLocal}")
    private boolean isLocal;
    private final FileUploadHelper fileUploadHelper;

    @Override
    public void upload(UploadFile uploadFile) throws IOException {
        if(isLocal){
            fileUploadHelper.uploadOnLocal(uploadFile);
        }else{
            fileUploadHelper.uploadOnS3(uploadFile);
        }
    }
}

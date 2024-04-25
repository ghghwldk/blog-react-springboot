package com.m.blog.aggregate.file.adapter.file;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.BlogFile;
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
    public void upload(BlogFile blogFile) throws IOException {
        byte[] data = blogFile.getData();
        String fileKey = blogFile.getFileKey();
        String originalFileName = blogFile.getOriginalFileName();

        if(fileProperties.isForLocal()){
            fileUploadUtil.uploadOnLocal(data, fileKey);
        }else{
            fileUploadUtil.uploadOnS3(originalFileName, fileKey, data);
        }
    }
}

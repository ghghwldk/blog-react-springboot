package com.m.blog.aggregate.file.adapter.out.file;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.port.out.file.FileUploadPort;
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

        fileUploadUtil.upload(fileKey, data);
    }
}

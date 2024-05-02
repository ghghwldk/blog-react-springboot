package com.m.blog.aggregate.file.adapter.out.file;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.file.FileUploadPort;
import com.m.blog.aggregate.file.infrastructure.file.FileUploadUtil;
import com.m.blog.global.exception.GetFileNullException;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Adapter
@RequiredArgsConstructor
public class FileUploadAdapter implements FileUploadPort {
    private final FileProperties fileProperties;
    private final FileUploadUtil fileUploadUtil;

    @Override
    public void upload(File_ file) throws IOException {
        byte[] data = file.getUploadData().orElseThrow(GetFileNullException::new);
        String fileKey = file.getInternalFileKey();

        fileUploadUtil.upload(fileKey, data);
    }
}

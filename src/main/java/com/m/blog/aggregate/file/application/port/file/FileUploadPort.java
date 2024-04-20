package com.m.blog.aggregate.file.application.port.file;

import com.m.blog.aggregate.file.application.domain.UploadedFile;

import java.io.IOException;

public interface FileUploadPort {
    void upload(UploadedFile uploadedFile) throws IOException;
}

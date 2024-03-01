package com.m.blog.domain.file.application.port.file;

import com.m.blog.domain.file.application.domain.UploadFile;

import java.io.IOException;

public interface FileUploadPort {
    void upload(UploadFile uploadFile) throws IOException;
}

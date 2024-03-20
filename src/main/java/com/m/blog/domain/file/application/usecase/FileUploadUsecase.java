package com.m.blog.domain.file.application.usecase;

import com.m.blog.domain.file.application.domain.UploadedFile;

import java.io.IOException;

public interface FileUploadUsecase {
    void upload(UploadedFile uploadedFile) throws IOException;
}

package com.m.blog.file.application.usecase;

import com.m.blog.file.application.domain.UploadedFile;

import java.io.IOException;

public interface FileUploadUsecase {
    void upload(UploadedFile uploadedFile) throws IOException;
}

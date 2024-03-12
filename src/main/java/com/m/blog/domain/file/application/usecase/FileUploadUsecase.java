package com.m.blog.domain.file.application.usecase;

import com.m.blog.domain.file.application.domain.UploadFile;

import java.io.IOException;

public interface FileUploadUsecase {
    void upload(UploadFile uploadFile) throws IOException;
}

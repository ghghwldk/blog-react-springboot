package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.UploadedFile;

import java.io.IOException;

public interface FileUploadUsecase {
    void upload(UploadedFile uploadedFile) throws IOException;
}

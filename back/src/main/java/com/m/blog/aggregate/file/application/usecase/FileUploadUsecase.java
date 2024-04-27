package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.File_;

import java.io.IOException;

public interface FileUploadUsecase {
    void upload(File_ file) throws IOException;
}

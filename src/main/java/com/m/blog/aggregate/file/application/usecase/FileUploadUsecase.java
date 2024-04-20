package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.BlogFile;

import java.io.IOException;

public interface FileUploadUsecase {
    void upload(BlogFile blogFile) throws IOException;
}

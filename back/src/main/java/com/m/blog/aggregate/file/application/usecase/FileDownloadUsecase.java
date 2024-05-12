package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.File_;

import java.io.IOException;

public interface FileDownloadUsecase {
    File_ download(File_.FileId condition) throws IOException;
}

package com.m.blog.aggregate.file.application.port.out.file;

import com.m.blog.aggregate.file.application.domain.File_;

import java.io.IOException;

public interface FileUploadPort {
    void upload(File_ file) throws IOException;
}

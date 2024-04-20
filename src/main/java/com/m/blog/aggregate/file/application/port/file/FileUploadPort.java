package com.m.blog.aggregate.file.application.port.file;

import com.m.blog.aggregate.file.application.domain.BlogFile;

import java.io.IOException;

public interface FileUploadPort {
    void upload(BlogFile blogFile) throws IOException;
}

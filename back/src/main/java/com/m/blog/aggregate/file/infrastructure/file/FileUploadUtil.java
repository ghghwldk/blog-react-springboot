package com.m.blog.aggregate.file.infrastructure.file;

import com.m.blog.aggregate.file.application.domain.BlogFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileUploadUtil {
    void upload(String fileKey, byte[] data) throws IOException;
}

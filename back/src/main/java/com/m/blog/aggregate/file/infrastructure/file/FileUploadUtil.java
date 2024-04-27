package com.m.blog.aggregate.file.infrastructure.file;

import java.io.IOException;

public interface FileUploadUtil {
    void upload(String fileKey, byte[] data) throws IOException;
}

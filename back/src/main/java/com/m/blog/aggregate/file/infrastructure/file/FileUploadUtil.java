package com.m.blog.aggregate.file.infrastructure.file;

import com.m.blog.aggregate.file.application.domain.BlogFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileUploadUtil {
    Optional<File> convert(String originalFileName, byte[] data) throws IOException;

    void uploadOnLocal(byte[] data, String fileKey) throws IOException;

    void uploadOnS3(String originalFileName, String fileKey, byte[] data) throws IOException;
}

package com.m.blog.aggregate.file.infrastructure.file;

import com.m.blog.aggregate.file.application.domain.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileUploadUtil {
    Optional<File> convert(UploadedFile uploadedFile) throws IOException;

    void removeNewFile(File targetFile);

    void uploadOnLocal(UploadedFile uploadedFile) throws IOException;

    void uploadOnS3(UploadedFile uploadedFile) throws IOException;
}

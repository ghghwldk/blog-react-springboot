package com.m.blog.domain.file.infrastructure.file;

import com.m.blog.domain.file.application.domain.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileUploadHelper {
    Optional<File> convert(UploadFile uploadFile) throws IOException;

    void removeNewFile(File targetFile);

    void uploadOnLocal(UploadFile uploadFile) throws IOException;

    void uploadOnS3(UploadFile uploadFile) throws IOException;
}

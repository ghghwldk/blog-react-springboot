package com.m.blog.domain.file.infrastructure.file;

import com.m.blog.domain.file.domain.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileUpload {
    Optional<File> convert(MultipartFile file) throws IOException;

    void removeNewFile(File targetFile);

    void uploadOnLocal(UploadFile fileVo) throws IOException;

    void uploadOnS3(UploadFile fileVo) throws IOException;
}

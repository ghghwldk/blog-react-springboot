package com.m.blog.aggregate.file.infrastructure.file;

import com.m.blog.aggregate.file.application.domain.BlogFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileUploadUtil {
    Optional<File> convert(BlogFile blogFile) throws IOException;

    void remove(File targetFile);

    void uploadOnLocal(BlogFile blogFile) throws IOException;

    void uploadOnS3(BlogFile blogFile) throws IOException;
}

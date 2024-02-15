package com.m.blog.domain.file.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileUtil {
    Optional<File> convert(MultipartFile file) throws IOException;

    void removeNewFile(File targetFile);
}

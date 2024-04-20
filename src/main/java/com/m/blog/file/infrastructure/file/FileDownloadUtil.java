package com.m.blog.file.infrastructure.file;

import com.m.blog.file.application.domain.File;

import java.io.IOException;
import java.io.InputStream;

public interface FileDownloadUtil {
    InputStream getS3Resource(File info);

    InputStream getLocalResource(File info) throws IOException;
}

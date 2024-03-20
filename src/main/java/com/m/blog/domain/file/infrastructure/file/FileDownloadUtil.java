package com.m.blog.domain.file.infrastructure.file;

import com.m.blog.domain.file.application.domain.BaseFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileDownloadUtil {
    InputStream getS3Resource(BaseFile info);

    InputStream getLocalResource(BaseFile info) throws IOException;
}

package com.m.blog.domain.file.infrastructure.file;

import com.m.blog.domain.file.application.domain.DownloadFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileDownloadHelper {
    InputStream getS3Resource(DownloadFile fileVo);

    InputStream getLocalResource(DownloadFile fileVo) throws IOException;
}

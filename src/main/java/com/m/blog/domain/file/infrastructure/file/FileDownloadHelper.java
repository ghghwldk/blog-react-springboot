package com.m.blog.domain.file.infrastructure.file;

import com.m.blog.domain.file.application.domain.DownloadFileInfo;

import java.io.IOException;
import java.io.InputStream;

public interface FileDownloadHelper {
    InputStream getS3Resource(DownloadFileInfo fileVo);

    InputStream getLocalResource(DownloadFileInfo fileVo) throws IOException;
}

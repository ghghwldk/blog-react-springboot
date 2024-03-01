package com.m.blog.domain.file.infrastructure.file;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileDownload {
    Resource getS3Resource(DownloadFileVo fileVo);

    Resource getLocalResource(DownloadFileVo fileVo) throws IOException;
}

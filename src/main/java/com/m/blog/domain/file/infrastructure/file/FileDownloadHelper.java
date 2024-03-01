package com.m.blog.domain.file.infrastructure.file;

import com.m.blog.domain.file.domain.DownloadFile;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileDownloadHelper {
    Resource getS3Resource(DownloadFile fileVo);

    Resource getLocalResource(DownloadFile fileVo) throws IOException;
}

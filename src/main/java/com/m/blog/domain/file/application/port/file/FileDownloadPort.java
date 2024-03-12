package com.m.blog.domain.file.application.port.file;

import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public interface FileDownloadPort {

    InputStream getResource(DownloadFile fileVo) throws IOException;
}

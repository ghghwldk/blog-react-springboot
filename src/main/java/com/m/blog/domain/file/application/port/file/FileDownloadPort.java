package com.m.blog.domain.file.application.port.file;

import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileDownloadPort {

    Resource getResource(DownloadFile fileVo) throws IOException;
}

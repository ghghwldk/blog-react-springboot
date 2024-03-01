package com.m.blog.domain.file.application.port.entrypoint.api;

import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface FileDownloadPort {
    ResponseEntity<Resource> getResponse(FileDownloadRequest requestDto) throws IOException;
}

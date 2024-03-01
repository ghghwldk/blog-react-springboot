package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface FileDownloadPort {
    ResponseEntity<Resource> get(FileDownloadRequest requestDto) throws IOException;
}

package com.m.blog.aggregate.file.application.port.in.web;

import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface FileDownloadEndpointPort {
    FileDownloadResponse download(FileDownloadRequest request) throws IOException;
}

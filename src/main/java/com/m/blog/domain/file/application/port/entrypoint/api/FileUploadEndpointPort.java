package com.m.blog.domain.file.application.port.entrypoint.api;

import com.m.blog.domain.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadResponse;

import java.io.IOException;

public interface FileUploadEndpointPort {
    FileUploadResponse upload(FileUploadRequest request) throws IOException;
}

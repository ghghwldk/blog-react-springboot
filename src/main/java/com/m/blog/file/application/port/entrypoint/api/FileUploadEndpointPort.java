package com.m.blog.file.application.port.entrypoint.api;

import com.m.blog.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.file.infrastructure.web.dto.FileUploadResponse;

import java.io.IOException;

public interface FileUploadEndpointPort {
    FileUploadResponse upload(FileUploadRequest request) throws IOException;
}

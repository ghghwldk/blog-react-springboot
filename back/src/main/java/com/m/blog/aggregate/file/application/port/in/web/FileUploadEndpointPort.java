package com.m.blog.aggregate.file.application.port.in.web;

import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadResponse;

import java.io.IOException;

public interface FileUploadEndpointPort {
    FileUploadResponse upload(FileUploadRequest request) throws IOException;
}

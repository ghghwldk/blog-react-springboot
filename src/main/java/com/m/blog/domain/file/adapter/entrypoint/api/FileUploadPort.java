package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.domain.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadResponse;

import java.io.IOException;

public interface FileUploadPort {
    FileUploadResponse upload(FileUploadRequest requestDto) throws IOException;
}

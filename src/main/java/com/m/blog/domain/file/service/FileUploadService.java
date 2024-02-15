package com.m.blog.domain.file.service;

import com.m.blog.domain.file.dto.FileUploadRequest;
import com.m.blog.domain.file.dto.FileUploadResponse;

import java.io.IOException;

public interface FileUploadService {
    FileUploadResponse upload(FileUploadRequest requestDto) throws IOException;
}

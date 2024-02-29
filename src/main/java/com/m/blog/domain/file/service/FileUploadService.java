package com.m.blog.domain.file.service;

import com.m.blog.domain.file.adapter.in.FileUploadRequest;
import com.m.blog.domain.file.adapter.out.FileUploadResponse;

import java.io.IOException;

public interface FileUploadService {
    FileUploadResponse upload(FileUploadRequest requestDto) throws IOException;
}

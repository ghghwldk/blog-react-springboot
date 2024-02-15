package com.m.blog.domain.file.service;

import com.m.blog.domain.file.dto.FileDownloadRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface FileDownloadService {
    ResponseEntity<Resource> get(FileDownloadRequest requestDto) throws IOException;
}

package com.m.blog.domain.file.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface FileDownloadService {
    ResponseEntity<Resource> getObject(String fileName) throws IOException;
}

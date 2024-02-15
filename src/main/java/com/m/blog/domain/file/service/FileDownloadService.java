package com.m.blog.domain.file.service;

import com.m.blog.domain.file.dto.FileDownloadRequestDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface FileDownloadService {
    ResponseEntity<Resource> get(FileDownloadRequestDto requestDto) throws IOException;
}

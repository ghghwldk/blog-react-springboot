package com.m.blog.domain.file.service;

import com.m.blog.domain.file.dto.FileUploadRequestDto;
import com.m.blog.domain.file.dto.FileUploadResponseDto;

import java.io.IOException;

public interface FileUploadService {
    FileUploadResponseDto upload(FileUploadRequestDto requestDto) throws IOException;
}

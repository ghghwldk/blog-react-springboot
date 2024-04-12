package com.m.blog.domain.file.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@Builder
public class FileUploadRequest {
    private String postingId;
    private MultipartFile multipartFile;
}

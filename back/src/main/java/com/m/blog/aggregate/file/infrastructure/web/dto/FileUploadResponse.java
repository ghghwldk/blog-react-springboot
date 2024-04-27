package com.m.blog.aggregate.file.infrastructure.web.dto;

import com.m.blog.aggregate.file.application.domain.File_;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileUploadResponse {
    private String originalFileName;
    private String fileName;
    private String downloadUrl;

}
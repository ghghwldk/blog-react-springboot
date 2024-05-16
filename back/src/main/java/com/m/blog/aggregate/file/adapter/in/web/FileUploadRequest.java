package com.m.blog.aggregate.file.adapter.in.web;

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

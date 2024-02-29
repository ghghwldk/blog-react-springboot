package com.m.blog.domain.file.adapter.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@Builder
public class FileUploadRequest {
    private MultipartFile multipartFile;
}

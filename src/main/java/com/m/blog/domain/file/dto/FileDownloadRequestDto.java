package com.m.blog.domain.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileDownloadRequestDto {
    private String fileName;
}

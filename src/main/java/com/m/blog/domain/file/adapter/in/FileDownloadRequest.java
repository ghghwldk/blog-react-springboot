package com.m.blog.domain.file.adapter.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileDownloadRequest {
    private String fileName;
}

package com.m.blog.aggregate.file.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileDownloadRequest {
    private String id;
}

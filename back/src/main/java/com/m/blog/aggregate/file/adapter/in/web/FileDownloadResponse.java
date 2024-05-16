package com.m.blog.aggregate.file.adapter.in.web;

import com.m.blog.aggregate.file.application.domain.File_;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileDownloadResponse {
    private String originalFileName;
    private byte[] data;


}
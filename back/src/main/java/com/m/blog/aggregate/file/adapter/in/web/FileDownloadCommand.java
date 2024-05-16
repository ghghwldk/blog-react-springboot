package com.m.blog.aggregate.file.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileDownloadCommand {
    private String id;
}

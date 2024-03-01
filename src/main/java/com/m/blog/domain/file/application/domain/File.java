package com.m.blog.domain.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;

@AllArgsConstructor
@Builder
@Data
public class File {
    String fileName;
    String originalFileName;
    String filePath;
}
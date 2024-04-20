package com.m.blog.file.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DownloadTrialCondition {
    private BaseFile.FileId fileId;
}

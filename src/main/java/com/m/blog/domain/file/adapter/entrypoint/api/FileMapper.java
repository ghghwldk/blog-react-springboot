package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;

public class FileMapper {
    public static DownloadFile.TrialCondition of(FileDownloadRequest request){
        return DownloadFile.TrialCondition.builder()
                .fileName(request.getFileName())
                .build();
    }

    public static DownloadFile of(File file, DownloadFile.TrialCondition condition){
        return DownloadFile.builder()
                .path(file.getFilePath())
                .key(file.getFilePath() + "/" + condition.getFileName())
                .originalName(file.getOriginalFileName())
                .build();
    }
}

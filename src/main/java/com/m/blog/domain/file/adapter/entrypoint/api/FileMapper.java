package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.common.Mapper;
import com.m.blog.domain.file.application.domain.DownloadCondition;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;

@Mapper
public class FileMapper {
    public static DownloadCondition of(FileDownloadRequest request){
        return DownloadCondition.builder()
                .fileName(request.getFileName())
                .build();
    }
}

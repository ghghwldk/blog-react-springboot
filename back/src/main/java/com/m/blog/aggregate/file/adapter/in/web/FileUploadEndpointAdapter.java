package com.m.blog.aggregate.file.adapter.in.web;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.port.in.web.FileUploadEndpointPort;
import com.m.blog.aggregate.file.application.usecase.FileUploadUsecase;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadResponse;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class FileUploadEndpointAdapter implements FileUploadEndpointPort {
    private final FileUploadUsecase fileUploadUsecase;
    private final FileProperties fileProperties;

    @Override
    public FileUploadResponse upload(FileUploadRequest request) throws IOException{
        File_ file = FileEntrypointMapper.of(request, fileProperties.getDirectoryName());

        fileUploadUsecase.upload(
                file.setSnowflakeIdDuringUpload()
        );

        return FileEntrypointMapper.toUploadResponse(file);
    }


}

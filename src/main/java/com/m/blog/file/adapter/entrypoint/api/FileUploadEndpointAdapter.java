package com.m.blog.file.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.file.application.port.entrypoint.api.FileUploadEndpointPort;
import com.m.blog.file.application.usecase.FileUploadUsecase;
import com.m.blog.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.file.infrastructure.web.dto.FileUploadResponse;
import com.m.blog.file.application.domain.UploadedFile;
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
        UploadedFile uploadedFile = FileMapper.of(request, fileProperties.getDirectoryName());

        fileUploadUsecase.upload(uploadedFile);

        return FileUploadResponse.of(uploadedFile);
    }


}

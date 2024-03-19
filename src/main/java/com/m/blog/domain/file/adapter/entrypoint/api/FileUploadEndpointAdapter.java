package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.port.entrypoint.api.FileUploadEndpointPort;
import com.m.blog.domain.file.application.usecase.FileUploadUsecase;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadResponse;
import com.m.blog.domain.file.application.domain.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class FileUploadEndpointAdapter implements FileUploadEndpointPort {
    private final FileUploadUsecase fileUploadUsecase;

    @Value("${file.directory}") private String directoryName; // static

    @Override
    public FileUploadResponse upload(FileUploadRequest request) throws IOException{
        UploadFile uploadFile = FileMapper.of(request.getMultipartFile(), this.directoryName);

        fileUploadUsecase.upload(uploadFile);

        return FileUploadResponse.of(uploadFile);
    }


}

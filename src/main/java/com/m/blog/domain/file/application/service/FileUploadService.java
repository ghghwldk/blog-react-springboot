package com.m.blog.domain.file.application.service;

import com.m.blog.common.UseCase;
import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.domain.file.application.port.file.FileUploadPort;
import com.m.blog.domain.file.application.port.persistence.WriteFilePort;
import com.m.blog.domain.file.application.usecase.FileUploadUsecase;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@UseCase
@RequiredArgsConstructor
public class FileUploadService implements FileUploadUsecase {
    private final WriteFilePort writeFilePort;
    private final FileUploadPort fileUploadPort;

    @Override
    public void upload(UploadFile uploadFile) throws IOException {
        writeFilePort.save(uploadFile);
        fileUploadPort.upload(uploadFile);
    }
}

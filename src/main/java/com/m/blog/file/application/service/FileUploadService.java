package com.m.blog.file.application.service;

import com.m.blog.common.UseCase;
import com.m.blog.file.application.domain.UploadedFile;
import com.m.blog.file.application.port.file.FileUploadPort;
import com.m.blog.file.application.port.persistence.WriteFilePersistencePort;
import com.m.blog.file.application.usecase.FileUploadUsecase;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@UseCase
@RequiredArgsConstructor
public class FileUploadService implements FileUploadUsecase {
    private final WriteFilePersistencePort writeFilePersistencePort;
    private final FileUploadPort fileUploadPort;

    @Override
    public void upload(UploadedFile uploadedFile) throws IOException {
        writeFilePersistencePort.save(uploadedFile);
        fileUploadPort.upload(uploadedFile);
    }
}

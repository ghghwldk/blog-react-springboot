package com.m.blog.aggregate.file.application.service;

import com.m.blog.common.UseCase;
import com.m.blog.aggregate.file.application.domain.UploadedFile;
import com.m.blog.aggregate.file.application.port.file.FileUploadPort;
import com.m.blog.aggregate.file.application.port.persistence.WriteFilePersistencePort;
import com.m.blog.aggregate.file.application.usecase.FileUploadUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FileUploadUsecaseImpl implements FileUploadUsecase {
    private final WriteFilePersistencePort writeFilePersistencePort;
    private final FileUploadPort fileUploadPort;

    @Override
    public void upload(UploadedFile uploadedFile) throws IOException {
        writeFilePersistencePort.save(uploadedFile);
        fileUploadPort.upload(uploadedFile);
    }
}

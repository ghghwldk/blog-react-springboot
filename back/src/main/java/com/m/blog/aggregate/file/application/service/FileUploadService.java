package com.m.blog.aggregate.file.application.service;

import com.m.blog.global.customAnnotation.Usecase;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.file.FileUploadPort;
import com.m.blog.aggregate.file.application.port.out.persistence.WriteFilePersistencePort;
import com.m.blog.aggregate.file.application.usecase.FileUploadUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Usecase
@RequiredArgsConstructor
@Transactional
public class FileUploadService implements FileUploadUsecase {
    private final WriteFilePersistencePort writeFilePersistencePort;
    private final FileUploadPort fileUploadPort;

    @Override
    public void upload(File_ file) throws IOException {
        writeFilePersistencePort.save(file);
        fileUploadPort.upload(file);
    }
}

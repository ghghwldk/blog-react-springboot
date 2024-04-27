package com.m.blog.aggregate.file.application.service;

import com.m.blog.global.customAnnotation.UseCase;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.port.out.file.FileUploadPort;
import com.m.blog.aggregate.file.application.port.out.persistence.WriteFilePersistencePort;
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
    public void upload(BlogFile blogFile) throws IOException {
        writeFilePersistencePort.save(blogFile);
        fileUploadPort.upload(blogFile);
    }
}

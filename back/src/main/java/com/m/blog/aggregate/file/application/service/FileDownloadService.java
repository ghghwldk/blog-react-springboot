package com.m.blog.aggregate.file.application.service;

import com.m.blog.global.customAnnotation.Usecase;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.file.FileDownloadPort;
import com.m.blog.aggregate.file.application.port.out.persistence.ReadFilePersistencePort;
import com.m.blog.aggregate.file.application.usecase.FileDownloadUsecase;
import com.m.blog.global.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Usecase
@RequiredArgsConstructor
public class FileDownloadService implements FileDownloadUsecase {
    private final ReadFilePersistencePort readFilePersistencePort;
    private final FileDownloadPort fileDownloadPort;

    @Override
    public File_ download(String fileId) throws IOException {
        File_ found = readFilePersistencePort.find(fileId)
                .orElseThrow(DataNotFoundException::new);

        return  fileDownloadPort.get(found);
    }
}

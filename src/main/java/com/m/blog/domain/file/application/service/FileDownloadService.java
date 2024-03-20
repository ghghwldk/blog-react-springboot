package com.m.blog.domain.file.application.service;

import com.m.blog.common.UseCase;
import com.m.blog.domain.file.application.domain.DownloadResult;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.application.port.file.FileDownloadPort;
import com.m.blog.domain.file.application.port.persistence.ReadFilePersistencePort;
import com.m.blog.domain.file.application.usecase.FileDownloadUsecase;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@UseCase
@RequiredArgsConstructor
public class FileDownloadService implements FileDownloadUsecase {
    private final ReadFilePersistencePort readFilePersistencePort;
    private final FileDownloadPort fileDownloadPort;

    @Override
    public DownloadResult download(File.TrialCondition condition) throws IOException {
        File file = readFilePersistencePort.get(condition);

        return  fileDownloadPort.get(file);
    }
}

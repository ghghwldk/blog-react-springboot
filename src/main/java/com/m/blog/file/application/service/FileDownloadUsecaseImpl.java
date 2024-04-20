package com.m.blog.file.application.service;

import com.m.blog.common.UseCase;
import com.m.blog.file.application.domain.DownloadedFile;
import com.m.blog.file.application.domain.File;
import com.m.blog.file.application.domain.DownloadTrialCondition;
import com.m.blog.file.application.port.file.FileDownloadPort;
import com.m.blog.file.application.port.persistence.ReadFilePersistencePort;
import com.m.blog.file.application.usecase.FileDownloadUsecase;
import com.m.blog.global.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class FileDownloadUsecaseImpl implements FileDownloadUsecase {
    private final ReadFilePersistencePort readFilePersistencePort;
    private final FileDownloadPort fileDownloadPort;

    @Override
    public DownloadedFile download(DownloadTrialCondition condition) throws IOException {
        Optional<File> found = readFilePersistencePort.get(condition);

        if(found.isEmpty()){
            throw new DataNotFoundException();
        }
        return  fileDownloadPort.get(found.get());
    }
}

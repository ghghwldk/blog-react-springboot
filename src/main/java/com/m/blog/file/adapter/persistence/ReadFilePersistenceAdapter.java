package com.m.blog.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.file.application.domain.File;
import com.m.blog.file.application.domain.DownloadTrialCondition;
import com.m.blog.file.application.port.persistence.ReadFilePersistencePort;
import com.m.blog.file.infrastructure.repository.FileJpaRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;


@Adapter
@RequiredArgsConstructor
public class ReadFilePersistenceAdapter implements ReadFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;


    @Override
    public File get(DownloadTrialCondition condition) throws IOException {
        return fileJpaRepository.findById(condition.getFileId().getValue())
                .map(FileJpaMapper::toDomain)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public File findByFileName(String fileName) {
        return fileJpaRepository.findById(fileName)
                .map(FileJpaMapper::toDomain)
                .orElseThrow(EntityNotFoundException::new);
    }
}

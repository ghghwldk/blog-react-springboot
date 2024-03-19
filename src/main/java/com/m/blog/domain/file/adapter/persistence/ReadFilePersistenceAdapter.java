package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.application.port.persistence.ReadFilePersistencePort;
import com.m.blog.domain.file.infrastructure.repository.FileJpaRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;


@Adapter
@RequiredArgsConstructor
public class ReadFilePersistenceAdapter implements ReadFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;


    @Override
    public DownloadFile get(DownloadFile.TrialCondition condition) throws IOException {
        File file = fileJpaRepository.findByAssignedFileName(condition.getAssignedFileName())
                .map(FileJpaMapper::toDomain)
                .orElseThrow(EntityNotFoundException::new);

        return DownloadFile.of(file);
    }

    @Override
    public File findByFileName(String fileName) {
        return fileJpaRepository.findByAssignedFileName(fileName)
                .map(FileJpaMapper::toDomain)
                .orElseThrow(EntityNotFoundException::new);
    }
}

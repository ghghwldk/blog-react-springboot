package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.BaseFile;
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
    public BaseFile get(BaseFile.TrialCondition condition) throws IOException {
        return fileJpaRepository.findByAssignedFileName(condition.getAssignedFileName())
                .map(FileJpaMapper::toDomain)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public BaseFile findByFileName(String fileName) {
        return fileJpaRepository.findByAssignedFileName(fileName)
                .map(FileJpaMapper::toDomain)
                .orElseThrow(EntityNotFoundException::new);
    }
}

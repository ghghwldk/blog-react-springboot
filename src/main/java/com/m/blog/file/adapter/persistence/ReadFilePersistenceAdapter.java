package com.m.blog.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.file.application.domain.File;
import com.m.blog.file.application.domain.DownloadTrialCondition;
import com.m.blog.file.application.port.persistence.ReadFilePersistencePort;
import com.m.blog.file.infrastructure.repository.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;


@Adapter
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadFilePersistenceAdapter implements ReadFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;


    @Override
    public Optional<File> get(DownloadTrialCondition condition) throws IOException {
        return fileJpaRepository.findById(condition.getFileId().getValue())
                .map(FilePersistenceMapper::toDomain);
    }

    @Override
    public Optional<File> findByFileName(String fileName) {
        return fileJpaRepository.findById(fileName)
                .map(FilePersistenceMapper::toDomain);
    }
}

package com.m.blog.aggregate.file.adapter.persistence;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.domain.BlogFile.DownloadTrialCondition;
import com.m.blog.aggregate.file.application.port.persistence.ReadFilePersistencePort;
import com.m.blog.aggregate.file.infrastructure.repository.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;


@Adapter
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadFilePersistenceAdapter implements ReadFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;


    @Override
    public Optional<BlogFile> get(BlogFile.DownloadTrialCondition condition) throws IOException {
        return fileJpaRepository.findById(condition.getFileId().getValue())
                .map(FilePersistenceMapper::toDomain);
    }

    @Override
    public Optional<BlogFile> findByFileName(String fileName) {
        return fileJpaRepository.findById(fileName)
                .map(FilePersistenceMapper::toDomain);
    }
}

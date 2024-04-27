package com.m.blog.aggregate.file.adapter.out.persistence;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.persistence.ReadFilePersistencePort;
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
    public Optional<File_> get(File_ condition) throws IOException {
        return fileJpaRepository.findById(condition.getFileId().getValue())
                .map(FilePersistenceMapper::toDomain);
    }
}

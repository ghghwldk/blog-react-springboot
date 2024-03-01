package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.domain.file.application.port.persistence.ReadFilePort;
import lombok.RequiredArgsConstructor;


@Adapter
@RequiredArgsConstructor
public class ReadFileAdapter implements ReadFilePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public File findByFileName(String fileName) {
        return fileJpaRepository.findByFileName(fileName)
                .map(FileJpaMapper::toDomain)
                .orElseThrow(RuntimeException::new);
    }
}

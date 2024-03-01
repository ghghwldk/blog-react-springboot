package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.domain.file.infrastructure.repository.FileEntity;
import com.m.blog.domain.file.port.persistence.ReadFilePort;

import java.util.Optional;


public class ReadFileAdapter implements ReadFilePort {
    @Override
    public Optional<FileEntity> findByFileName(String fileName) {
        return Optional.empty();
    }
}

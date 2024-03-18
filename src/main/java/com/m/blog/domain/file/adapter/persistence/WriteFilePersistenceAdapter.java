package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;
import com.m.blog.domain.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.domain.file.application.port.persistence.WriteFilePersistencePort;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class WriteFilePersistenceAdapter implements WriteFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public File save(UploadFile uploadFile){
        return FileJpaMapper.toDomain(fileJpaRepository.save(FileEntity.of(uploadFile)));
    }
}

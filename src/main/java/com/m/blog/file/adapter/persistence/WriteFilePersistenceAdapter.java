package com.m.blog.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.file.application.domain.File;
import com.m.blog.file.application.domain.UploadedFile;
import com.m.blog.file.infrastructure.repository.FileEntity;
import com.m.blog.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.file.application.port.persistence.WriteFilePersistencePort;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class WriteFilePersistenceAdapter implements WriteFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public File save(UploadedFile uploadedFile){
        FileEntity saved = fileJpaRepository.save(FilePersistenceMapper.of(uploadedFile));

        return FilePersistenceMapper.toDomain(saved);
    }
}

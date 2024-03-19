package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.adapter.entrypoint.api.FileMapper;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;
import com.m.blog.domain.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.domain.file.application.port.persistence.WriteFilePersistencePort;
import com.m.blog.domain.file.infrastructure.repository.FilePersistenceMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class WriteFilePersistenceAdapter implements WriteFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public File save(UploadFile uploadFile){
        FileEntity saved = fileJpaRepository.save(FilePersistenceMapper.of(uploadFile));

        return FileJpaMapper.toDomain(saved);
    }
}

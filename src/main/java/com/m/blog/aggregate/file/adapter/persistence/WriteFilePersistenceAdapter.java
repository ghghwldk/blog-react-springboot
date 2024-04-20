package com.m.blog.aggregate.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.aggregate.file.application.domain.File;
import com.m.blog.aggregate.file.application.domain.UploadedFile;
import com.m.blog.aggregate.file.infrastructure.repository.FileEntity;
import com.m.blog.aggregate.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.aggregate.file.application.port.persistence.WriteFilePersistencePort;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class WriteFilePersistenceAdapter implements WriteFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public void save(UploadedFile uploadedFile){
        fileJpaRepository.save(FilePersistenceMapper.of(uploadedFile));
    }
}

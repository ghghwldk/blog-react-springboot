package com.m.blog.aggregate.file.adapter.persistence;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.UploadedFile;
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

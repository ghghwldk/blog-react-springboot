package com.m.blog.aggregate.file.adapter.out.persistence;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.aggregate.file.application.port.out.persistence.WriteFilePersistencePort;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class WriteFilePersistenceAdapter implements WriteFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public void save(File_ file){
        fileJpaRepository.save(FilePersistenceMapper.of(file));
    }
}

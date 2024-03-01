package com.m.blog.domain.file.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.domain.File;
import com.m.blog.domain.file.domain.UploadFile;
import com.m.blog.domain.file.infrastructure.repository.FileEntity;
import com.m.blog.domain.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.domain.file.port.persistence.WriteFilePort;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class WriteFileAdapter implements WriteFilePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public File save(UploadFile uploadFile, String directoryName){
        return FileJpaMapper.toDomain(fileJpaRepository.save(FileEntity.of(uploadFile, directoryName)));
    }
}

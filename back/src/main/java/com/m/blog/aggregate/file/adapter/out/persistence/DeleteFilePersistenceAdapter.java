package com.m.blog.aggregate.file.adapter.out.persistence;

import com.m.blog.aggregate.file.application.domain.FileId;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.persistence.DeleteFilePersistencePort;
import com.m.blog.global.customAnnotation.Adapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Adapter
@RequiredArgsConstructor
public class DeleteFilePersistenceAdapter implements DeleteFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public void deleteAll(List<FileId> deleteTargets){
        fileJpaRepository.deleteAllById(deleteTargets.stream()
                .map(FileId::getValue)
                .collect(Collectors.toList()));
    }
}

package com.m.blog.aggregate.file.adapter.out.persistence;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.persistence.DeleteFilePersistencePort;
import com.m.blog.aggregate.file.infrastructure.repository.FileJpaRepository;
import com.m.blog.global.customAnnotation.Adapter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Adapter
@RequiredArgsConstructor
public class DeleteFilePersistenceAdapter implements DeleteFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public void deleteAll(List<File_.FileId> deleteTargets){
        fileJpaRepository.deleteAllById(deleteTargets.stream()
                .map(File_.FileId::getValue)
                .collect(Collectors.toList()));
    }
}

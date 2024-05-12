package com.m.blog.aggregate.file.adapter.out.persistence;

import com.m.blog.aggregate.file.infrastructure.repository.FileEntity;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.persistence.ReadFilePersistencePort;
import com.m.blog.aggregate.file.infrastructure.repository.FileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Adapter
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadFilePersistenceAdapter implements ReadFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public Optional<File_> find(File_.FileId condition){
        return fileJpaRepository.findById(condition.getValue())
                .map(e-> FilePersistenceMapper.of(condition, e));
    }

    @Override
    public List<File_.FileId> findAll(Posting.PostingId postingId){
        List<FileEntity> founds =  fileJpaRepository.findAllByPostingId(postingId.getValue());

        return founds.stream()
                .map(found-> File_.getByPostingId(found.getId()))
                .collect(Collectors.toList());
    }
}

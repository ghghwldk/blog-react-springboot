package com.m.blog.aggregate.file.adapter.out.persistence;

import com.m.blog.aggregate.file.application.domain.FileId;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.application.domain.PostingId;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.persistence.ReadFilePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Adapter
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadFilePersistenceAdapter implements ReadFilePersistencePort {
    private final FileJpaRepository fileJpaRepository;

    @Override
    public Optional<File_> find(String fileId){
        return fileJpaRepository.findById(fileId)
                .map(e-> FilePersistenceMapper.of(fileId, e));
    }

    @Override
    public List<FileId> findAll(PostingId postingId){
        List<FileEntity> founds =  fileJpaRepository.findAllByPostingId(postingId.getValue());

        return founds.stream()
                .map(found-> File_.getByPostingId(found.getId()))
                .collect(Collectors.toList());
    }
}

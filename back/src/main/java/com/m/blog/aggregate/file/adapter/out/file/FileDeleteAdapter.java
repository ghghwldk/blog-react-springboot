package com.m.blog.aggregate.file.adapter.out.file;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.persistence.DeleteFilePersistencePort;
import com.m.blog.aggregate.file.application.port.out.persistence.ReadFilePersistencePort;
import com.m.blog.aggregate.file.application.usecase.FileDeleteUsecase;
import com.m.blog.aggregate.file.infrastructure.file.FileDeleteUtil;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.global.customAnnotation.Adapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Adapter
@RequiredArgsConstructor
@Slf4j
public class FileDeleteAdapter implements FileDeleteUsecase {
    private final FileDeleteUtil fileDeleteUtil;
    private final DeleteFilePersistencePort deleteFilePersistencePort;
    private final ReadFilePersistencePort readFilePersistencePort;

    @Override
    public void deleteUsing(List<File_.FileId> existings, Posting.PostingId postingId){
        List<String> wholeFileIds =  readFilePersistencePort.findAll(postingId).stream()
                .map(File_.FileId::getValue)
                .collect(Collectors.toList());
        List<String> existingIds = existings.stream()
                .map(File_.FileId::getValue)
                .collect(Collectors.toList());

        List<File_.FileId> deleteTargets = wholeFileIds.stream()
                .filter(fileId -> !existingIds.contains(fileId))
                .map(File_.FileId::new)
                .collect(Collectors.toList());

        deleteFilePersistencePort.deleteAll(deleteTargets);
        fileDeleteUtil.wait(deleteTargets);
    }
}


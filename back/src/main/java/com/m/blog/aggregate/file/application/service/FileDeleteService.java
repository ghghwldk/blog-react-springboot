package com.m.blog.aggregate.file.application.service;

import com.m.blog.aggregate.file.adapter.out.file.util.FileDeleteUtil;
import com.m.blog.aggregate.file.application.domain.FileId;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.persistence.DeleteFilePersistencePort;
import com.m.blog.aggregate.file.application.port.out.persistence.ReadFilePersistencePort;
import com.m.blog.aggregate.file.application.usecase.FileDeleteUsecase;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.application.domain.PostingId;
import com.m.blog.global.customAnnotation.Adapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Adapter
@RequiredArgsConstructor
@Slf4j
public class FileDeleteService implements FileDeleteUsecase {
    private final FileDeleteUtil fileDeleteUtil;
    private final DeleteFilePersistencePort deleteFilePersistencePort;
    private final ReadFilePersistencePort readFilePersistencePort;

    @Override
    public void deleteUsing(List<FileId> existings, PostingId postingId){
        List<FileId> whole =  readFilePersistencePort.findAll(postingId);
        List<FileId> deleteTargets = File_.getDeleteTargets(existings, whole);

        deleteFilePersistencePort.deleteAll(deleteTargets);
        fileDeleteUtil.wait(deleteTargets);
    }
}


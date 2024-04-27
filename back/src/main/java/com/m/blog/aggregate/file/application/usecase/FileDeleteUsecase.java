package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.File_;

import java.util.List;

public interface FileDeleteUsecase {
    void delete(List<File_.FileId> fileIds);
}

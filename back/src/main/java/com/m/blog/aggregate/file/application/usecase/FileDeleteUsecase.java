package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.BaseFile;

import java.util.List;

public interface FileDeleteUsecase {
    void delete(List<BaseFile.FileId> fileIds);
}

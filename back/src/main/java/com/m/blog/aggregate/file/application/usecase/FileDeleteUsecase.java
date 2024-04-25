package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.BlogFile;

import java.util.List;

public interface FileDeleteUsecase {
    void delete(List<BlogFile.FileId> fileIds);
}

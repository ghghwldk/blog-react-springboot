package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.FileId;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.application.domain.PostingId;

import java.io.IOException;
import java.util.List;

public interface FileDeleteUsecase {
    void deleteUsing(List<FileId> existing, PostingId postingId);
}

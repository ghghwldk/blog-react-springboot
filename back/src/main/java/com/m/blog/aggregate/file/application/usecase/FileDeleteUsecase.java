package com.m.blog.aggregate.file.application.usecase;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.posting.application.domain.Posting;

import java.io.IOException;
import java.util.List;

public interface FileDeleteUsecase {
    void deleteUsing(List<File_.FileId> existing, Posting.PostingId postingId);
}

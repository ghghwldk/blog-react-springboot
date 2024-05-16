package com.m.blog.aggregate.posting.application.usecase;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.posting.adapter.in.web.PostingUpdateCommand;
import com.m.blog.aggregate.posting.application.domain.Posting;

import java.util.List;

public interface ChangePostingUsecase {
    List<File_.FileId> update(PostingUpdateCommand command);
}

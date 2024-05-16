package com.m.blog.aggregate.posting.application.usecase;

import com.m.blog.aggregate.posting.adapter.in.web.PostingCreateCommand;

public interface SavePostingUsecase {
    void save(PostingCreateCommand command);
}

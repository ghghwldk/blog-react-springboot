package com.m.blog.aggregate.posting.application.usecase;

import com.m.blog.aggregate.posting.application.domain.Posting;

public interface SavePostingUsecase {
    void save(Posting posting);
}

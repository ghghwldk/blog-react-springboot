package com.m.blog.domain.posting.application.usecase;

import com.m.blog.domain.posting.application.domain.Posting;

public interface SavePostingUsecase {
    void save(Posting posting);
}

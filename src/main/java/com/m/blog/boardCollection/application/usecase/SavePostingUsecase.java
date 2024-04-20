package com.m.blog.boardCollection.application.usecase;

import com.m.blog.boardCollection.application.domain.Posting;

public interface SavePostingUsecase {
    void save(Posting posting);
}

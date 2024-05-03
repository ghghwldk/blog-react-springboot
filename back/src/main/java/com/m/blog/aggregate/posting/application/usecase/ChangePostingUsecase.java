package com.m.blog.aggregate.posting.application.usecase;

import com.m.blog.aggregate.posting.application.domain.Posting;

public interface ChangePostingUsecase {
    void update(Posting after);
}

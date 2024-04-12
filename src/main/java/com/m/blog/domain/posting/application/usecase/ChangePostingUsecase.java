package com.m.blog.domain.posting.application.usecase;

import com.m.blog.domain.posting.application.domain.Posting;

public interface ChangePostingUsecase {
    void update(Posting after);
}

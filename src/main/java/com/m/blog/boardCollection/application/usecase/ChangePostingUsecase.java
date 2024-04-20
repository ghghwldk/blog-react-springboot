package com.m.blog.boardCollection.application.usecase;

import com.m.blog.boardCollection.application.domain.Posting;

public interface ChangePostingUsecase {
    void update(Posting after);
}

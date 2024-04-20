package com.m.blog.boardCollection.application.port.persistence;

import com.m.blog.boardCollection.application.domain.Posting;

public interface ChangePostingPersistencePort {
    void update(Posting after);
}

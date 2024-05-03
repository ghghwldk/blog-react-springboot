package com.m.blog.aggregate.posting.application.port.out.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.BoardCollection;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;

public interface ChangePostingPersistencePort {
    void update(Posting after);
}

package com.m.blog.aggregate.boardCollection.application.port.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;

public interface SavePostingPersistencePort {
    void save(Posting posting);
}

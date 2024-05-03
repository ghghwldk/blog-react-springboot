package com.m.blog.aggregate.posting.application.port.out.persistence;

import com.m.blog.aggregate.posting.application.domain.Posting;

public interface SavePostingPersistencePort {
    void save(Posting posting);
}

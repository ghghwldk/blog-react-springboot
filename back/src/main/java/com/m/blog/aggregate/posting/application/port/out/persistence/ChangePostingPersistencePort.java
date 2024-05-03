package com.m.blog.aggregate.posting.application.port.out.persistence;

import com.m.blog.aggregate.posting.application.domain.Posting;

public interface ChangePostingPersistencePort {
    void update(Posting after);
}

package com.m.blog.domain.posting.application.port.persistence;

import com.m.blog.domain.posting.application.domain.Posting;

public interface ChangePostingPersistencePort {
    void update(Posting.PostingId id, Posting.Mutable target);
}

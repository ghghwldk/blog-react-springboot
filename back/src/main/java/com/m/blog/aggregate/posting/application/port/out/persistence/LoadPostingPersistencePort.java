package com.m.blog.aggregate.posting.application.port.out.persistence;

import com.m.blog.aggregate.posting.application.domain.Posting;

import java.util.Optional;

public interface LoadPostingPersistencePort {
    Optional<Posting> load(Posting.PostingId postingId);
}

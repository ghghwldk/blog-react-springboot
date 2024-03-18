package com.m.blog.domain.posting.application.port.persistence;

import com.m.blog.domain.posting.application.domain.Posting;

public interface FindPostingPersistencePort {
    Posting.Sophisticated get(Posting.PostingId condition);

}

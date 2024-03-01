package com.m.blog.domain.posting.application.port.persistence;

import com.m.blog.domain.posting.application.domain.Posting;

public interface SavePostingPort {
    void save(Posting.PostingId id, Posting.Mutable target);
}

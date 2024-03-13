package com.m.blog.domain.posting.application.port.persistence;

import com.m.blog.domain.posting.application.domain.Posting;

public interface FindPostingNewIdPort {
    Posting.NewId findNewId(Posting.IdWithoutPostingId id);
}

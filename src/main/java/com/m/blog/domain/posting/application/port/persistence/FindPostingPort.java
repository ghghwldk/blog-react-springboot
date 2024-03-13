package com.m.blog.domain.posting.application.port.persistence;

import com.m.blog.domain.posting.application.domain.Posting;

public interface FindPostingPort {
    Posting.Sophisticated get(Posting.PostingId condition);

}

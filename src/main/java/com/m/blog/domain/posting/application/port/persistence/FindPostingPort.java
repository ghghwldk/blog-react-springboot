package com.m.blog.domain.posting.application.port.persistence;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.repository.PostingDto;
import org.springframework.data.domain.Pageable;

public interface FindPostingPort {
    PostingDto get(Posting.SingleCondition condition);

}

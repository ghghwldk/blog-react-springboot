package com.m.blog.domain.posting.application.query;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;
import org.springframework.data.domain.Pageable;

public interface FindPostingQuery {
    PagingResponse get(Posting.IdWithoutPostingId idWithoutPostingId, Pageable pageable);
    PagingResponse getPagingResponse(Pageable pageable);
    PostingReadResponse get(Posting.PostingId condition);
}

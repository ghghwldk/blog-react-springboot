package com.m.blog.aggregate.posting.application.query;

import com.m.blog.aggregate.boardCollection.application.domain.Board;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;
import org.springframework.data.domain.Pageable;

public interface FindPostingQuery {
    PagingResponse get(Board.BoardId boardId, Pageable pageable);
    PagingResponse getPagingResponse(Pageable pageable);
    PostingReadResponse get(Posting.PostingId condition);
}

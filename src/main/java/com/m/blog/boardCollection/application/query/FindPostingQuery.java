package com.m.blog.boardCollection.application.query;

import com.m.blog.boardCollection.application.domain.Board;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;
import org.springframework.data.domain.Pageable;

public interface FindPostingQuery {
    PagingResponse get(Board.BoardId boardId, Pageable pageable);
    PagingResponse getPagingResponse(Pageable pageable);
    PostingReadResponse get(Posting.PostingId condition);
}

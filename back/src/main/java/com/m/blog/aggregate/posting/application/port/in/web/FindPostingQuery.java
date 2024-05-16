package com.m.blog.aggregate.posting.application.port.in.web;

import com.m.blog.aggregate.board.application.domain.Board;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.adapter.in.web.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;
import org.springframework.data.domain.Pageable;

public interface FindPostingQuery {
    PagingResponse get(String boardId, Pageable pageable);
    PagingResponse getPagingResponse(Pageable pageable);
    PostingReadResponse get(String condition);
}

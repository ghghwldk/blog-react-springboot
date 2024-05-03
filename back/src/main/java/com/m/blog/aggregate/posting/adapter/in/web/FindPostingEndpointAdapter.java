package com.m.blog.aggregate.posting.adapter.in.web;

import com.m.blog.aggregate.boardCollection.application.domain.Board;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingReadPerBoardPagingRequest;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingReadRequest;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.application.port.in.web.FindPositngEndpointPort;
import com.m.blog.aggregate.posting.application.query.FindPostingQuery;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class FindPostingEndpointAdapter implements FindPositngEndpointPort {
    private final FindPostingQuery findPostingQuery;

    @Override
    public PagingResponse getPagingResponse(PostingReadPerBoardPagingRequest request){
        return findPostingQuery.get(
                new Board.BoardId(request.getBoardId()),
                request.getPageable()
        );
    }


    @Override
    public PagingResponse getPagingResponse(PostingReadPagingRequest request) {
        return findPostingQuery.getPagingResponse(request.getPageable());
    }

    @Override
    public PostingReadResponse get(PostingReadRequest request){
        Posting.PostingId condition = new Posting.PostingId(request.getId());

        return findPostingQuery.get(condition);
    }
}

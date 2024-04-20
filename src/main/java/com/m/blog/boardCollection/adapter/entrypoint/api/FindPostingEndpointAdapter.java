package com.m.blog.boardCollection.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.application.port.entrypoint.api.FindPositngEndpointPort;
import com.m.blog.boardCollection.application.query.FindPostingQuery;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadPerBoardPagingRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class FindPostingEndpointAdapter implements FindPositngEndpointPort {
    private final FindPostingQuery findPostingQuery;

    @Override
    public PagingResponse getPagingResponse(PostingReadPerBoardPagingRequest request){
        return findPostingQuery.get(
                PostingMapper.of(request),
                request.getPageable()
        );
    }


    @Override
    public PagingResponse getPagingResponse(PostingReadPagingRequest request) {
        return findPostingQuery.getPagingResponse(request.getPageable());
    }

    @Override
    public PostingReadResponse get(PostingReadRequest request){
        Posting.PostingId condition = Posting.get(request.getId());

        return findPostingQuery.get(condition);
    }
}

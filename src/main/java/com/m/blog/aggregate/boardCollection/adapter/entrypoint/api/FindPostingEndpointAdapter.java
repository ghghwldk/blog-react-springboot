package com.m.blog.aggregate.boardCollection.adapter.entrypoint.api;

import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.PostingReadPerBoardPagingRequest;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.PostingReadRequest;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.aggregate.boardCollection.application.port.entrypoint.api.FindPositngEndpointPort;
import com.m.blog.aggregate.boardCollection.application.query.FindPostingQuery;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class FindPostingEndpointAdapter implements FindPositngEndpointPort {
    private final FindPostingQuery findPostingQuery;

    @Override
    public PagingResponse getPagingResponse(PostingReadPerBoardPagingRequest request){
        return findPostingQuery.get(
                PostingEntrypointMapper.of(request),
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

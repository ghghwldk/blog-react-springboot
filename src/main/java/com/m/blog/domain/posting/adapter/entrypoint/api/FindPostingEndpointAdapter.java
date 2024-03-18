package com.m.blog.domain.posting.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.entrypoint.api.FindPositngEndpointPort;
import com.m.blog.domain.posting.application.usecase.FindPostingUsecase;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadFilteredPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class FindPostingEndpointAdapter implements FindPositngEndpointPort {
    private final FindPostingUsecase findPostingUsecase;

    @Override
    public PagingResponse getPagingResponse(PostingReadFilteredPagingRequest request){
        return findPostingUsecase.get(
                PostingMapper.of(request),
                request.getPageable()
        );
    }


    @Override
    public PagingResponse getPagingResponse(PostingReadPagingRequest request) {
        return findPostingUsecase.getPagingResponse(request.getPageable());
    }

    @Override
    public PostingReadResponse get(PostingReadRequest request){
        Posting.PostingId condition =
                Posting.get(request.getBoardCollectionId(), request.getBoardId(), request.getId());

        return findPostingUsecase.get(condition);
    }
}

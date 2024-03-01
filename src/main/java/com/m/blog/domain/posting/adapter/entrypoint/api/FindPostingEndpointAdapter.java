package com.m.blog.domain.posting.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.board.application.port.out.BoardDto;
import com.m.blog.domain.board.application.port.out.GetBoardQuery;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.entrypoint.api.FindPositngEndpointPort;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPagingPort;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPort;
import com.m.blog.domain.posting.application.usecase.FindPostingUsecase;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadFilteredPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Adapter
@RequiredArgsConstructor
public class FindPostingEndpointAdapter implements FindPositngEndpointPort {
    private final FindPostingUsecase findPostingUsecase;
    private final PostingDtoMapper postingDtoMapper;

    @Override
    public PagingResponse getPagingResponse(PostingReadFilteredPagingRequest request){
        return findPostingUsecase.get(
                postingDtoMapper.of(request),
                request.getPageable()
        );
    }


    @Override
    public PagingResponse getPagingResponse(PostingReadPagingRequest request) {
        return findPostingUsecase.getPagingResponse(request.getPageable());
    }

    @Override
    public PostingReadResponse get(PostingReadRequest request){
        Posting.SingleCondition condition =
                Posting.get(request.getBoardCollectionId(), request.getBoardId(), request.getId());

        return findPostingUsecase.get(condition);
    }
}

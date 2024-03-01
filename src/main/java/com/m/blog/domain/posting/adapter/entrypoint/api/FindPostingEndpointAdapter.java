package com.m.blog.domain.posting.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.board.application.port.out.BoardDto;
import com.m.blog.domain.board.application.port.out.GetBoardQuery;
import com.m.blog.domain.posting.application.port.entrypoint.api.FindPositngEndpointPort;
import com.m.blog.domain.posting.application.port.persistence.DslPostingPort;
import com.m.blog.domain.posting.infrastructure.repository.PostingJpaRepository;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadFilteredPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class FindPostingEndpointAdapter implements FindPositngEndpointPort {
    private final DslPostingPort postingDslRepository;
    private final PostingJpaRepository postingJpaRepository;
    private final GetBoardQuery getBoardQuery;

    @Override
    public PagingResponse getPagingResponse(PostingReadFilteredPagingRequest requestDto){
        BoardDto found = getBoardQuery
                .findBoardDto(requestDto.getBoardCollectionId(), requestDto.getBoardId());

        return PagingResponse.get(postingDslRepository.get(requestDto), found);
    }


    @Override
    public PagingResponse getPagingResponse(PostingReadPagingRequest requestDto) {
        BoardDto found = null;

        return PagingResponse.get(postingDslRepository.get(requestDto), found);
    }
    @Override
    public PostingReadResponse get(PostingReadRequest requestDto){
        return PostingReadResponse.of(postingDslRepository.get(requestDto));
    }
}

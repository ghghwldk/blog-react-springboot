package com.m.blog.domain.posting.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.board.application.port.out.BoardDto;
import com.m.blog.domain.board.application.port.out.GetBoardQuery;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.entrypoint.api.FindPositngEndpointPort;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPagingPort;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPort;
import com.m.blog.domain.posting.infrastructure.repository.PostingDslRepository;
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
    private final GetBoardQuery getBoardQuery;
    private final FindPostingPort findPostingPort;
    private final FindPostingPagingPort findPostingPagingPort;


    @Override
    public PagingResponse getPagingResponse(PostingReadFilteredPagingRequest request){
        BoardDto found = getBoardQuery
                .findBoardDto(request.getBoardCollectionId(), request.getBoardId());

        Posting.InBoardCondition condition = Posting.forFilteredPage(request.getBoardCollectionId(), request.getBoardId());

        return PagingResponse.get(findPostingPagingPort.getFilteredPage(condition, request.getPageable()), found);
    }

    @Override
    public PagingResponse getPagingResponse(PostingReadPagingRequest request) {
        BoardDto found = null;

        return PagingResponse.get(findPostingPagingPort.getPaging(request.getPageable()), found);
    }

    @Override
    public PostingReadResponse get(PostingReadRequest request){
        Posting.SingleCondition condition =
                Posting.get(request.getBoardCollectionId(), request.getBoardId(), request.getId());
        return PostingReadResponse.of(findPostingPort.get(condition));
    }
}

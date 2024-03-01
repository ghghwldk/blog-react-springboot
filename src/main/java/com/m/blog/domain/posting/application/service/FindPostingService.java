package com.m.blog.domain.posting.application.service;

import com.m.blog.domain.board.application.port.out.BoardDto;
import com.m.blog.domain.board.application.port.out.GetBoardQuery;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPagingPort;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPort;
import com.m.blog.domain.posting.application.usecase.FindPostingUsecase;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindPostingService implements FindPostingUsecase {
    private final GetBoardQuery getBoardQuery;
    private final FindPostingPagingPort findPostingPagingPort;
    private final FindPostingPort findPostingPort;

    @Override
    public PagingResponse get(Posting.IdWithoutPostingId idWithoutPostingId, Pageable pageable){
        BoardDto found = getBoardQuery
                .findBoardDto(idWithoutPostingId.getBoardCollectionId(), idWithoutPostingId.getBoardId());

        Posting.InBoardCondition condition =
                Posting.forFilteredPage(idWithoutPostingId.getBoardCollectionId(), idWithoutPostingId.getBoardId());

        return PagingResponse.get(findPostingPagingPort.getFilteredPage(condition, pageable), found);
    }

    @Override
    public PagingResponse getPagingResponse(Pageable pageable) {
        BoardDto found = null;

        return PagingResponse.get(findPostingPagingPort.getPaging(pageable), found);
    }

    @Override
    public PostingReadResponse get(Posting.PostingId condition){
        return PostingReadResponse.of(findPostingPort.get(condition));
    }
}
package com.m.blog.domain.posting.application.service;

import com.m.blog.domain.board.infrastructure.repository.BoardDto;
import com.m.blog.domain.board.infrastructure.repository.BoardDslRepository;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPagingPersistencePort;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPersistencePort;
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
class FindPostingService implements FindPostingUsecase {
    private final BoardDslRepository boardDslRepository;
    private final FindPostingPagingPersistencePort findPostingPagingPersistencePort;
    private final FindPostingPersistencePort findPostingPersistencePort;

    @Override
    public PagingResponse get(Posting.IdWithoutPostingId idWithoutPostingId, Pageable pageable){
        BoardDto found = boardDslRepository
                .findBoardDto(idWithoutPostingId.getBoardCollectionId(), idWithoutPostingId.getBoardId());

        Posting.InBoardCondition condition =
                Posting.forFilteredPage(idWithoutPostingId.getBoardCollectionId(), idWithoutPostingId.getBoardId());

        return PagingResponse.get(findPostingPagingPersistencePort.getFilteredPage(condition, pageable), found);
    }

    @Override
    public PagingResponse getPagingResponse(Pageable pageable) {
        BoardDto found = null;

        return PagingResponse.get(findPostingPagingPersistencePort.getPaging(pageable), found);
    }

    @Override
    public PostingReadResponse get(Posting.PostingId condition){
        return PostingReadResponse.of(findPostingPersistencePort.get(condition));
    }
}
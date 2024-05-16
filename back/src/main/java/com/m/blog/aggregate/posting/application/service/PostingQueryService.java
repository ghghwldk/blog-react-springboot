package com.m.blog.aggregate.posting.application.service;

import com.m.blog.aggregate.board.adapter.out.persistence.BoardDslRepository;
import com.m.blog.aggregate.board.adapter.out.persistence.BoardDto;
import com.m.blog.aggregate.posting.adapter.in.web.PostingReadResponse;
import com.m.blog.aggregate.posting.adapter.out.persistence.PostingDslRepository;
import com.m.blog.aggregate.posting.adapter.out.persistence.PostingDto;
import com.m.blog.aggregate.posting.application.port.in.web.FindPostingQuery;
import com.m.blog.global.customAnnotation.Usecase;
import com.m.blog.global.exception.DataNotFoundException;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Usecase
@RequiredArgsConstructor
@Transactional
class PostingQueryService implements FindPostingQuery {
    private final PostingDslRepository postingDslRepository;
    private final BoardDslRepository boardDslRepository;

    @Override
    public PagingResponse get(String boardId, Pageable pageable){
        BoardDto found = boardDslRepository
                .findBoardDto(boardId)
                .orElseThrow(DataNotFoundException::new);


        return PagingResponse.get(getPagePerBoard(boardId, pageable), found);
    }

    @Override
    public PagingResponse getPagingResponse(Pageable pageable) {
        return PagingResponse.get(getPage(pageable), null);
    }
    public Page<PostingDto> getPage(Pageable pageable) {
        return postingDslRepository.getPage(pageable);
    }

    public Page<PostingDto> getPagePerBoard(String boardId, Pageable pageable) {
        return postingDslRepository
                .getPagePerBoard(boardId, pageable);
    }

    @Override
    public PostingReadResponse get(String postingId){
        return PostingReadResponse.of(postingDslRepository.getSinglePage(postingId));
    }
}
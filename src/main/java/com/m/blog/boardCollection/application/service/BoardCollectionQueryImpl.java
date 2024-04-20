package com.m.blog.boardCollection.application.service;

import com.m.blog.boardCollection.application.domain.Board;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.application.query.FindPostingQuery;
import com.m.blog.boardCollection.application.query.MenuQuery;
import com.m.blog.boardCollection.infrastructure.repository.*;
import com.m.blog.boardCollection.infrastructure.web.dto.MenuResponse;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.common.Query;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


@Query
@Transactional(readOnly = true)
@RequiredArgsConstructor
class BoardCollectionQueryImpl implements FindPostingQuery, MenuQuery {
    private final BoardDslRepository boardDslRepository;
    private final PostingDslRepository postingDslRepository;
    private final BoardCollectionJpaRepository boardCollectionJpaRepository;
    private final BoardCollectionDslRepository boardCollectionDslRepository;

    @Override
    public MenuResponse get(){
        return MenuResponse.of(
                boardCollectionJpaRepository.findAll()
                , boardCollectionDslRepository.getAggregationDtos()
        );
    }
    public Page<PostingDto> getPage(Pageable pageable) {
        return postingDslRepository.getPage(pageable);
    }

    public Page<PostingDto> getPagePerBoard(Board.BoardId boardId, Pageable pageable) {
        return postingDslRepository
                .getPagePerBoard(boardId.getValue(), pageable);
    }

    @Override
    public PagingResponse get(Board.BoardId boardId, Pageable pageable){
        BoardDto found = boardDslRepository
                .findBoardDto(boardId.getValue());


        return PagingResponse.get(getPagePerBoard(boardId, pageable), found);
    }

    @Override
    public PagingResponse getPagingResponse(Pageable pageable) {
        return PagingResponse.get(getPage(pageable), null);
    }

    @Override
    public PostingReadResponse get(Posting.PostingId condition){
        return PostingReadResponse.of(postingDslRepository.getSinglePage(condition));
    }
}
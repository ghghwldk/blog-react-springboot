package com.m.blog.domain.board.infrastructure.repository;

public interface BoardDslRepository {
    BoardDto findBoardDto(int boardCollectionId, int boardId);
}

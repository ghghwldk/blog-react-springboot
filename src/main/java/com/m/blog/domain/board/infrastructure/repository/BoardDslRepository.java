package com.m.blog.domain.board.infrastructure.repository;

public interface BoardDslRepository {
    BoardDto findBoardDto(String boardId);
}

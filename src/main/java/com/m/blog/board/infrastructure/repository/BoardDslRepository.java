package com.m.blog.board.infrastructure.repository;

public interface BoardDslRepository {
    BoardDto findBoardDto(String boardId);
}

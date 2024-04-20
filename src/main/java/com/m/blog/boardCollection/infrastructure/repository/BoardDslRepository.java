package com.m.blog.boardCollection.infrastructure.repository;

public interface BoardDslRepository {
    BoardDto findBoardDto(String boardId);
}

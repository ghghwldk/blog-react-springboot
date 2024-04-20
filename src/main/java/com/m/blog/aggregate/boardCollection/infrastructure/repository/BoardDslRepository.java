package com.m.blog.aggregate.boardCollection.infrastructure.repository;

public interface BoardDslRepository {
    BoardDto findBoardDto(String boardId);
}

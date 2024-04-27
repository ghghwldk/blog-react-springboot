package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import java.util.Optional;

public interface BoardDslRepository {
    Optional<BoardDto> findBoardDto(String boardId);
}

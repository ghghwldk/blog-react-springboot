package com.m.blog.aggregate.board.infrastructure.repository;

import java.util.Optional;

public interface BoardDslRepository {
    Optional<BoardDto> findBoardDto(String boardId);
}

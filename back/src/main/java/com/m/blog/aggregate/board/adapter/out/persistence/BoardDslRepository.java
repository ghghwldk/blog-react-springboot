package com.m.blog.aggregate.board.adapter.out.persistence;

import java.util.Optional;

public interface BoardDslRepository {
    Optional<BoardDto> findBoardDto(String boardId);
}

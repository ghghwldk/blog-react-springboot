package com.m.blog.domain.board.repository;

import com.m.blog.domain.board.dto.BoardDto;

public interface BoardDslRepository {
    BoardDto findBoardDto(int boardCollectionId, int boardId);
}

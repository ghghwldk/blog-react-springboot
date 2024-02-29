package com.m.blog.domain.board.application.port.out;

public interface GetBoardQuery {
    BoardDto findBoardDto(int boardCollectionId, int boardId);
}

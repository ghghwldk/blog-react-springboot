package com.m.blog.aggregate.board.application.domain;

import com.m.blog.aggregate.boardCollection.application.domain.BoardCollectionId;
import lombok.*;


public class Board {
    @Getter private final BoardId boardId;
    @Getter private final BoardCollectionId boardCollectionId;
    @Getter private String name;
    @Getter private String description;

    public Board(@NonNull String boardId, @NonNull String boardCollectionId) {
        this.boardId = new BoardId(boardId);
        this.boardCollectionId = new BoardCollectionId(boardCollectionId);
    }
}

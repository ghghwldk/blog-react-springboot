package com.m.blog.aggregate.boardCollection.application.domain;

import com.m.blog.aggregate.posting.application.domain.Posting;
import lombok.*;

import java.util.List;


public class Board {
    @Getter private final BoardId boardId;
    @Getter private final BoardCollection.BoardCollectionId boardCollectionId;
    @Getter private String name;
    @Getter private String description;

    public Board(@NonNull String boardId, @NonNull String boardCollectionId) {
        this.boardId = new BoardId(boardId);
        this.boardCollectionId = new BoardCollection.BoardCollectionId(boardCollectionId);
    }

    @Getter
    @AllArgsConstructor
    public static class BoardId {
        private String value;
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof BoardId))
                return false;

            return this.value.equals(((BoardId) o).getValue());
        }
    }
}

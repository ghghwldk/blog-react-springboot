package com.m.blog.boardCollection.application.domain;

import com.m.blog.boardCollection.application.domain.BoardCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class Board {
    BoardId boardId;
    BoardCollection.Id boardCollectionId;
    String name;
    String description;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class BoardId {
        private String value;
    }
}

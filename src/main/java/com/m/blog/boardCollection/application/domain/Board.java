package com.m.blog.boardCollection.application.domain;

import lombok.*;

@Getter
@Builder
public class Board {
    @NonNull private final BoardId boardId;
    @NonNull private final BoardCollection.BoardCollectionId boardCollectionId;
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private final PostingLine postingLine;

    void changeBoardName(@NonNull String after){
        // some additional business logic if needed
        this.name = after;
    }

    void changeDescription(@NonNull String after){
        // some additional business logic if needed
        this.description = after;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class BoardId {
        private String value;
    }
}

package com.m.blog.aggregate.boardCollection.application.domain;

import lombok.*;
import org.thymeleaf.util.StringUtils;


public class Board {
    @Getter private final BoardId boardId;
    @Getter private final BoardCollection.BoardCollectionId boardCollectionId;
    @Getter private String name;
    @Getter private String description;
    private final PostingWindow postingWindow;
    @Getter private boolean isBoardAdded = false;
    @Getter private boolean isBoardUpdated = false;
    @Getter private boolean isPostingAdded = false;
    @Getter private boolean isPostingUpdated = false;

    public Board(@NonNull String boardId, @NonNull String boardCollectionId, @NonNull PostingWindow postingWindow) {
        this.boardId = new BoardId(boardId);
        this.boardCollectionId = new BoardCollection.BoardCollectionId(boardCollectionId);
        this.postingWindow = postingWindow;
    }

    void change(@NonNull Board after){
        this.name = after.name;
        this.description = after.name;

        isBoardUpdated = true;
    }

    void change(@NonNull Posting after){
        this.postingWindow.update(after);

        isPostingUpdated = true;
    }

    void add(@NonNull Posting posting){
        this.postingWindow.add(posting);
        isPostingAdded = true;
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

    Posting getUpdated(){
        return postingWindow.getUpdated();
    }

    Posting getAdded(){
        return postingWindow.getAdded();
    }
}

package com.m.blog.aggregate.boardCollection.application.domain;

import lombok.*;


public class Board {
    @Getter private final BoardId boardId;
    @Getter private final BoardCollection.BoardCollectionId boardCollectionId;
    @Getter private String name;
    @Getter private String description;
    private final PostingStore postingStore;
    @Getter private boolean isBoardAdded = false;
    @Getter private boolean isBoardUpdated = false;
    @Getter private boolean isPostingAdded = false;
    @Getter private boolean isPostingUpdated = false;

    public Board(@NonNull String boardId, @NonNull String boardCollectionId, @NonNull PostingStore postingStore) {
        this.boardId = new BoardId(boardId);
        this.boardCollectionId = new BoardCollection.BoardCollectionId(boardCollectionId);
        this.postingStore = postingStore;
    }

    void change(@NonNull Board after){
        this.name = after.name;
        this.description = after.name;

        isBoardUpdated = true;
    }

    void change(@NonNull Posting after){
        this.postingStore.update(after);

        isPostingUpdated = true;
    }

    void add(@NonNull Posting posting){
        this.postingStore.add(posting);
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
        return postingStore.getUpdated();
    }

    Posting getAdded(){
        return postingStore.getAdded();
    }
}

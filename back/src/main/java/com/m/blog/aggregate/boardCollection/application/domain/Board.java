package com.m.blog.aggregate.boardCollection.application.domain;

import lombok.*;

import java.util.List;


public class Board {
    @Getter private final BoardId boardId;
    @Getter private final BoardCollection.BoardCollectionId boardCollectionId;
    @Getter private String name;
    @Getter private String description;
    private final _PostingStore postingStore;

    public Board(@NonNull String boardId, @NonNull String boardCollectionId, @NonNull _PostingStore postingStore) {
        this.boardId = new BoardId(boardId);
        this.boardCollectionId = new BoardCollection.BoardCollectionId(boardCollectionId);
        this.postingStore = postingStore;
    }

    void update(@NonNull Board after){
        this.name = after.name;
        this.description = after.name;
    }

    void update(@NonNull Posting after){
        this.postingStore.update(after);
    }

    void save(@NonNull Posting posting){
        this.postingStore.save(posting);
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

    List<Posting> getUpdatedPostings(){
        return postingStore.getUpdatedPostings();
    }

    List<Posting> getSavedPostings(){
        return postingStore.getSavedPostings();
    }
}

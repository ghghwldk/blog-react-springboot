package com.m.blog.boardCollection.application.domain;

import lombok.*;

@AllArgsConstructor
public class BoardCollection implements BoardCollectionRepository{
    @NonNull private final BoardCollection.BoardCollectionId boardCollectionId;
    @NonNull private String name;
    @NonNull private final BoardWindow boardWindow;

    public String remove(@NonNull Board.BoardId boardId){
        return boardWindow.remove(boardId);
    }


    @Data
    @Builder
    @AllArgsConstructor
    public static class BoardCollectionId {
        private int id;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostingUpsertInfo {
        private BoardCollection.BoardCollectionId boardCollectionId;
        private Board.BoardId boardId;
        private Posting posting;
    }

    @Override
    public Posting getUpdatedPosting(){
        return this.boardWindow.getUpdatedPosting();
    }

    @Override
    public Posting getAddedPosting(){
        return this.boardWindow.getAddedPosting();
    }

    @Override
    public void add(Posting posting){
        this.boardWindow.add(posting);
    }

    @Override
    public void update(Posting posting){
        this.boardWindow.update(posting);
    }
}

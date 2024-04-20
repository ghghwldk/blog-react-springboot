package com.m.blog.aggregate.boardCollection.application.domain;

import lombok.*;

import java.util.List;


public class BoardCollection{
    @NonNull private final BoardCollection.BoardCollectionId boardCollectionId;
    @NonNull private String name;
    private BoardWindow boardWindow;

    @Builder
    public BoardCollection(BoardCollection.BoardCollectionId boardCollectionId, String name, BoardWindow boardWindow){
        this.boardCollectionId = boardCollectionId;
        this.name = name;
        this.boardWindow = boardWindow;
    }

    public String remove(@NonNull Board.BoardId boardId){
        return boardWindow.remove(boardId);
    }


    @Data
    @Builder
    @AllArgsConstructor
    public static class BoardCollectionId {
        private String value;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostingUpsertInfo {
        private BoardCollection.BoardCollectionId boardCollectionId;
        private Board.BoardId boardId;
        private Posting posting;
    }

    public Posting getUpdatedPosting(){
        return this.boardWindow.getUpdatedPosting();
    }

    public Posting getAddedPosting(){
        return this.boardWindow.getAddedPosting();
    }

    public void add(Board board){
        if(this.boardWindow == null){
            this.boardWindow = new BoardWindow(List.of(board));
        }

        boardWindow.add(board);
    }

    public void add(Posting posting){
        this.boardWindow.add(posting);
    }

    public void update(Posting posting){
        this.boardWindow.update(posting);
    }
}

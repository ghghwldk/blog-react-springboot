package com.m.blog.boardCollection.application.domain;

import lombok.*;

@Getter
@AllArgsConstructor
public class BoardCollection {
    @NonNull private final BoardCollection.BoardCollectionId boardCollectionId;
    @NonNull private String name;
    @NonNull private final BoardLine boardLine;


    public String remove(@NonNull Board.BoardId boardId){
        return boardLine.remove(boardId);
    }


    @Data
    @Builder
    @AllArgsConstructor
    public static class BoardCollectionId {
        private int id;
    }

}

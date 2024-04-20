package com.m.blog.boardCollection.application.domain;

import lombok.*;

@Getter
@AllArgsConstructor
public class BoardCollection {
    @NonNull private final BoardCollection.BoardCollectionId boardCollectionId;
    @NonNull private String name;
    @NonNull private final BoardLine boardLine;
    @NonNull private final PostingLine postingLine;


    public String removeBoard(@NonNull Board.BoardId boardId){
        return boardLine.remove(boardId);
    }

    public String removePosting(@NonNull Posting.PostingId postingId){
        return postingLine.remove(postingId);
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class BoardCollectionId {
        private int id;
    }

}

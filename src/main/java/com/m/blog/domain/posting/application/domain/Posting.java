package com.m.blog.domain.posting.application.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Posting {
    PostingId id;
    String title;
    String content;

    public static Posting.InBoardCondition forFilteredPage(int boardCollectionId, int boardId){
        return Posting.InBoardCondition.builder()
                .boardCollectionId(boardCollectionId)
                .boardId(boardId)
                .build();
    }

    public static Posting.PostingId get(int boardCollectionId, int boardId, int postingId){
        return Posting.PostingId.builder()
                .boardCollectionId(boardCollectionId)
                .boardId(boardId)
                .postingId(postingId)
                .build();
    }

    public static Posting withId(
            int postingId, int boardCollectionId, int boardId,
            String title, String content) {
        return Posting.builder()
                .id(PostingId.builder()
                        .boardCollectionId(boardCollectionId)
                        .boardId(boardId)
                        .postingId(postingId)
                        .build())
                .title(title)
                .content(content)
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostingId{
        private int boardCollectionId;
        private int boardId;
        private int postingId;

        public PostingId(IdWithoutPostingId idWithoutPostingId, int postingId){
            this.boardCollectionId = idWithoutPostingId.getBoardCollectionId();
            this.boardId = idWithoutPostingId.getBoardId();
            this.postingId = postingId;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class IdWithoutPostingId{
        private int boardCollectionId;
        private int boardId;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class InBoardCondition{
        private int boardCollectionId;
        private int boardId;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Mutable {
        private String title;
        private String content;
    }
}

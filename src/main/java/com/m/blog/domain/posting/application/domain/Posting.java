package com.m.blog.domain.posting.application.domain;

import com.m.blog.domain.posting.infrastructure.repository.PostingDto;
import lombok.*;

import java.time.LocalDateTime;

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


    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostingId{
        private int boardCollectionId;
        private int boardId;
        private int postingId;

        public static PostingId from(IdWithoutPostingId idWithoutPostingId, int postingId){
            return PostingId.builder()
                    .boardCollectionId(idWithoutPostingId.getBoardCollectionId())
                    .boardId(idWithoutPostingId.getBoardId())
                    .postingId(postingId)
                    .build();
        }
    }

    public static NewId getNextId(int maxId){
        return NewId.builder()
                .value(maxId + 1)
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class IdWithoutPostingId{
        private int boardCollectionId;
        private int boardId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class NewId{
        private int value;
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

package com.m.blog.domain.posting.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
public class Posting {
    PostingId postingId;
    Integer boardId;
    Integer boardCollectionId;
    String title;
    String content;

    public static Posting withoutId(
            int boardId, int boardCollectionId) {
        return Posting.builder()
                .boardCollectionId(boardCollectionId)
                .boardId(boardId)
                .build();
    }

    public static Posting withId(
            int postingId, int boardCollectionId, int boardId) {
        return Posting.builder()
                .postingId(new PostingId(postingId))
                .boardCollectionId(boardCollectionId)
                .boardId(boardId)
                .build();
    }

    @Value
    public static class PostingId{
        private int value;
    }
}

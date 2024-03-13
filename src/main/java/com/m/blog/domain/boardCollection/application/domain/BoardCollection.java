package com.m.blog.domain.boardCollection.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BoardCollection {
    private BoardCollectionId key;
    private String name;

    @Data
    @Builder
    @AllArgsConstructor
    public static class BoardCollectionId{
        private int id;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Aggregation {
        private int boardCollectionId;
        private String boardCollectionName;
        private int boardId;
        private String boardName;
        private long postingCount;
    }
}

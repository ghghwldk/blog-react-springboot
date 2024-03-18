package com.m.blog.domain.menu.infrastructure.web.dto;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MenuResponse {
    List<AggregationPerBoardCollection> nesteds;

    @Data
    @Builder
    @AllArgsConstructor
    public static class AggregationPerBoardCollection {
        String boardCollectionName;
        int boardCollectionId;
        int postingCount;
        List<AggregationPerBoard> aggregationPerBoards;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class AggregationPerBoard {
        private int boardCollectionId;
        private String boardCollectionName;
        private int boardId;
        private String boardName;
        private long postingCount;
    }
}

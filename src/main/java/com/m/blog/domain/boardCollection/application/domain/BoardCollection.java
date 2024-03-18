package com.m.blog.domain.boardCollection.application.domain;

import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public static class AggregationPerBoard {
        private int boardCollectionId;
        private String boardCollectionName;
        private int boardId;
        private String boardName;
        private long postingCount;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class AggregationPerBoardCollection {
        String boardCollectionName;
        int boardCollectionId;
        int postingCount;
        List<AggregationPerBoard> aggregationPerBoards;
    }

    public static List<AggregationPerBoardCollection> of(List<BoardCollection> boardCollections,
                                  List<BoardCollection.AggregationPerBoard> aggregationPerBoards){
        Map<Integer, List<AggregationPerBoard>> dtoPerBoardCollections = aggregationPerBoards.stream()
                .collect(Collectors.groupingBy(BoardCollection.AggregationPerBoard::getBoardCollectionId,
                        Collectors.toList()));

        return boardCollections.stream()
                .map(bc -> {
                    List<BoardCollection.AggregationPerBoard> filtered = dtoPerBoardCollections.get(bc.getKey().getId());
                    return AggregationPerBoardCollection.builder()
                            .boardCollectionName(bc.getName())
                            .boardCollectionId(bc.getKey().getId())
                            .postingCount(filtered != null ? filtered.size() : 0)
                            .aggregationPerBoards(filtered)
                            .build();
                })
                .collect(Collectors.toList());
    }
}

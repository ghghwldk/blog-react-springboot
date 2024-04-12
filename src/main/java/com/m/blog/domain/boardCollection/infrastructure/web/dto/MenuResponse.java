package com.m.blog.domain.boardCollection.infrastructure.web.dto;

import com.m.blog.domain.boardCollection.infrastructure.repository.BoardAggregationDto;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String boardCollectionId;
        long postingCount;
        List<BoardAggregationDto> aggregationPerBoards;
    }


    public static MenuResponse of(List<BoardCollectionEntity> boardCollectionEntities,
                                                         List<BoardAggregationDto> aggregationPerBoards){
        Map<String, List<BoardAggregationDto>> perBoardCollection = aggregationPerBoards.stream()
                .collect(Collectors.groupingBy(BoardAggregationDto::getBoardCollectionId,
                        Collectors.toList()));

        List<AggregationPerBoardCollection> nesteds = boardCollectionEntities.stream()
                .map(bce -> {
                    List<BoardAggregationDto> perBoard =
                            perBoardCollection.get(bce.getId());

                    return AggregationPerBoardCollection.builder()
                            .boardCollectionName(bce.getName())
                            .boardCollectionId(bce.getId())
                            .postingCount(perBoard != null ? perBoard.size() : 0)
                            .aggregationPerBoards(perBoard)
                            .build();
                })
                .collect(Collectors.toList());

        return MenuResponse.builder()
                .nesteds(nesteds)
                .build();
    }
}

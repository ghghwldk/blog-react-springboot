package com.m.blog.domain.menu.infrastructure.web.dto;

import com.m.blog.domain.boardCollection.adapter.out.BoardAggregationDto;
import com.m.blog.domain.boardCollection.adapter.out.BoardCollectionEntity;
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
    List<Nested> nesteds;

    @Data
    @Builder
    @AllArgsConstructor
    public static class Nested {
        String boardCollectionName;
        int boardCollectionId;
        int postingCount;
        List<BoardAggregationDto> boardInformationInMenuDtos;
    }

    public static MenuResponse of(List<BoardCollectionEntity> boardCollectionEntities,
                                  List<BoardAggregationDto> boardAggregationDtos){
        Map<Integer, List<BoardAggregationDto>> dtoPerBoardCollections = boardAggregationDtos.stream()
                .collect(Collectors.groupingBy(BoardAggregationDto::getBoardCollectionId,
                        Collectors.toList()));

        List<MenuResponse.Nested> nesteds = boardCollectionEntities.stream()
                .map(bc -> {
                    List<BoardAggregationDto> filtered = dtoPerBoardCollections.get(bc.getId());
                    return MenuResponse.Nested.builder()
                            .boardCollectionName(bc.getName())
                            .boardCollectionId(bc.getId())
                            .postingCount(filtered != null ? filtered.size() : 0)
                            .boardInformationInMenuDtos(filtered)
                            .build();
                })
                .collect(Collectors.toList());

        return MenuResponse.builder()
                .nesteds(nesteds)
                .build();
    }
}

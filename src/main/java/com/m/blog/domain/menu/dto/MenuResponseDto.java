package com.m.blog.domain.menu.dto;

import com.m.blog.domain.boardCollection.dto.BoardAggregationDto;
import com.m.blog.domain.boardCollection.entity.BoardCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class MenuResponseDto {
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

    public static MenuResponseDto of(List<BoardCollection> boardCollections,
                                List<BoardAggregationDto> boardAggregationDtos){
        Map<Integer, List<BoardAggregationDto>> dtoPerBoardCollections = boardAggregationDtos.stream()
                .collect(Collectors.groupingBy(BoardAggregationDto::getBoardCollectionId,
                        Collectors.toList()));

        List<MenuResponseDto.Nested> nesteds = boardCollections.stream()
                .map(bc -> {
                    List<BoardAggregationDto> filtered = dtoPerBoardCollections.get(bc.getId());
                    return MenuResponseDto.Nested.builder()
                            .boardCollectionName(bc.getName())
                            .boardCollectionId(bc.getId())
                            .postingCount(filtered != null ? filtered.size() : 0)
                            .boardInformationInMenuDtos(filtered)
                            .build();
                })
                .collect(Collectors.toList());

        return MenuResponseDto.builder()
                .nesteds(nesteds)
                .build();
    }
}

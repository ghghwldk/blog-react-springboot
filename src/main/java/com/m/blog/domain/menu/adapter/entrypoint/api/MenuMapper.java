package com.m.blog.domain.menu.adapter.entrypoint.api;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuMapper {
    public static MenuResponse of(List<BoardCollection> boardCollections,
                                  List<BoardCollection.Aggregation> aggregations){
        Map<Integer, List<BoardCollection.Aggregation>> dtoPerBoardCollections = aggregations.stream()
                .collect(Collectors.groupingBy(BoardCollection.Aggregation::getBoardCollectionId,
                        Collectors.toList()));

        List<MenuResponse.Nested> nesteds = boardCollections.stream()
                .map(bc -> {
                    List<BoardCollection.Aggregation> filtered = dtoPerBoardCollections.get(bc.getKey().getId());
                    return MenuResponse.Nested.builder()
                            .boardCollectionName(bc.getName())
                            .boardCollectionId(bc.getKey().getId())
                            .postingCount(filtered != null ? filtered.size() : 0)
                            .aggregations(filtered)
                            .build();
                })
                .collect(Collectors.toList());

        return MenuResponse.builder()
                .nesteds(nesteds)
                .build();
    }
}

package com.m.blog.domain.menu.adapter.entrypoint.api;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;

import java.util.List;
import java.util.stream.Collectors;

public class MenuMapper {
    private static MenuResponse.AggregationPerBoard of(BoardCollection.AggregationPerBoard before){
        return MenuResponse.AggregationPerBoard.builder()
                .boardCollectionId(before.getBoardCollectionId())
                .boardCollectionName(before.getBoardCollectionName())
                .boardId(before.getBoardId())
                .boardName(before.getBoardName())
                .postingCount(before.getPostingCount())
                .build();
    }

    private static MenuResponse.AggregationPerBoardCollection of
            (BoardCollection.AggregationPerBoardCollection before){
        return MenuResponse.AggregationPerBoardCollection.builder()
                .boardCollectionName(before.getBoardCollectionName())
                .boardCollectionId(before.getBoardCollectionId())
                .postingCount(before.getPostingCount())
                .aggregationPerBoards(before.getAggregationPerBoards().stream()
                        .map(MenuMapper::of)
                        .collect(Collectors.toList()))
                .build();
    }

    public static MenuResponse of
            (List<BoardCollection.AggregationPerBoardCollection> before){
        List<MenuResponse.AggregationPerBoardCollection> nesteds = before.stream()
                .map(MenuMapper::of).collect(Collectors.toList());

        return MenuResponse.builder()
                .nesteds(nesteds)
                .build();
    }
}

package com.m.blog.domain.boardCollection.infrastructure.repository;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;

public class BoardCollectionEntityMapper {
    public static BoardCollection.Aggregation of(BoardAggregationDto dto){
        return BoardCollection.Aggregation.builder()
                .boardCollectionId(dto.getBoardCollectionId())
                .boardCollectionName(dto.getBoardCollectionName())
                .boardId(dto.getBoardId())
                .boardName(dto.getBoardName())
                .postingCount(dto.getPostingCount())
                .build();
    }

    public static BoardCollection of(BoardCollectionEntity entity){
        return BoardCollection.builder()
                .key(BoardCollection.BoardCollectionId.builder()
                        .id(entity.getId())
                        .build())
                .name(entity.getName())
                .build();
    }
}

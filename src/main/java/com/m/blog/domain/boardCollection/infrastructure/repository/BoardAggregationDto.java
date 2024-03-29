package com.m.blog.domain.boardCollection.infrastructure.repository;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardAggregationDto {
    int boardCollectionId;
    String boardCollectionName;
    int boardId;
    String boardName;
    long postingCount;

    @QueryProjection
    public BoardAggregationDto(int boardCollectionId, String boardCollectionName,
                               int boardId, String boardName, long postingCount) {
        this.boardCollectionId = boardCollectionId;
        this.boardCollectionName = boardCollectionName;
        this.boardId = boardId;
        this.boardName = boardName;
        this.postingCount = postingCount;
    }
}

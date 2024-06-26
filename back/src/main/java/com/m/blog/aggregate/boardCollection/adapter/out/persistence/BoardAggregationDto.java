package com.m.blog.aggregate.boardCollection.adapter.out.persistence;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardAggregationDto {
    String boardCollectionId;
    String boardCollectionName;
    String boardId;
    String boardName;
    long postingCount;

    @QueryProjection
    public BoardAggregationDto(String boardCollectionId, String boardCollectionName,
                               String boardId, String boardName, long postingCount) {
        this.boardCollectionId = boardCollectionId;
        this.boardCollectionName = boardCollectionName;
        this.boardId = boardId;
        this.boardName = boardName;
        this.postingCount = postingCount;
    }
}

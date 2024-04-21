package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardCollectionIdDto {
    String boardCollectionId;

    @QueryProjection
    public BoardCollectionIdDto(String boardCollectionId) {
        this.boardCollectionId = boardCollectionId;
    }
}

package com.m.blog.domain.boardCollection.adapter.in;

import com.m.blog.domain.boardCollection.adapter.out.BoardAggregationDto;
import com.m.blog.domain.boardCollection.adapter.out.BoardCollectionEntity;

import java.util.List;

public interface GetBoardCollectionQuery {
    List<BoardCollectionEntity> getBoardCollectionEntities();
    List<BoardAggregationDto> getBoardAggregationDtos();
}

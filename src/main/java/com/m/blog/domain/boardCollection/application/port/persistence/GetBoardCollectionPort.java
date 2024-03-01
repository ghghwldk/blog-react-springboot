package com.m.blog.domain.boardCollection.application.port.persistence;

import com.m.blog.domain.boardCollection.infrastructure.repository.BoardAggregationDto;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionEntity;

import java.util.List;

public interface GetBoardCollectionPort {
    List<BoardCollectionEntity> getBoardCollectionEntities();
    List<BoardAggregationDto> getBoardAggregationDtos();
}

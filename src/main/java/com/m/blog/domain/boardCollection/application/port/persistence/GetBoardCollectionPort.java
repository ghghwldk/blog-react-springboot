package com.m.blog.domain.boardCollection.application.port.persistence;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardAggregationDto;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionEntity;

import java.util.List;

public interface GetBoardCollectionPort {
    List<BoardCollection> getBoardCollections();
    List<BoardCollection.Aggregation> getAggregations();
}

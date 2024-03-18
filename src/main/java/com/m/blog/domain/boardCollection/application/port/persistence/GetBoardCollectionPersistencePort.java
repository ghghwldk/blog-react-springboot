package com.m.blog.domain.boardCollection.application.port.persistence;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;

import java.util.List;

public interface GetBoardCollectionPersistencePort {
    List<BoardCollection> getBoardCollections();
    List<BoardCollection.AggregationPerBoard> getAggregations();
}

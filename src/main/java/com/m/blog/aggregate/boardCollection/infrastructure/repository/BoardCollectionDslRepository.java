package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import java.util.List;

public interface BoardCollectionDslRepository {
    List<BoardAggregationDto> getAggregationDtos();
}

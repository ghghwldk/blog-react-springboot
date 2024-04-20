package com.m.blog.boardCollection.infrastructure.repository;

import java.util.List;

public interface BoardCollectionDslRepository {
    List<BoardAggregationDto> getAggregationDtos();
}

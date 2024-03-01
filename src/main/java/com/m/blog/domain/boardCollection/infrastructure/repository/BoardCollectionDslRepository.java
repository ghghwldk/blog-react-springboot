package com.m.blog.domain.boardCollection.infrastructure.repository;

import java.util.List;

public interface BoardCollectionDslRepository {
    List<BoardAggregationDto> getBoardAggregationDtos();
}

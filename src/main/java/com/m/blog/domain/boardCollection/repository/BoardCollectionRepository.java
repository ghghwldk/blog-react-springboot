package com.m.blog.domain.boardCollection.repository;

import com.m.blog.domain.boardCollection.dto.BoardAggregationDto;

import java.util.List;

public interface BoardCollectionRepository {
    List<BoardAggregationDto> get();
}

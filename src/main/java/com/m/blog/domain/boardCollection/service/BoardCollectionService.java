package com.m.blog.domain.boardCollection.service;

import com.m.blog.domain.boardCollection.dto.BoardAggregationDto;
import com.m.blog.domain.boardCollection.dto.BoardInformationInMenuDto;
import com.m.blog.domain.boardCollection.entity.BoardCollection;

import java.util.List;

public interface BoardCollectionService {
    List<BoardCollection> findAll();


    List<BoardAggregationDto> get();
}

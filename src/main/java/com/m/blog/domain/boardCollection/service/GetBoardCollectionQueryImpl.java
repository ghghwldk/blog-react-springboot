package com.m.blog.domain.boardCollection.service;

import com.m.blog.domain.boardCollection.adapter.in.GetBoardCollectionQuery;
import com.m.blog.domain.boardCollection.adapter.out.BoardAggregationDto;
import com.m.blog.domain.boardCollection.adapter.out.BoardCollectionEntity;
import com.m.blog.domain.boardCollection.adapter.out.BoardCollectionDslRepository;
import com.m.blog.domain.boardCollection.adapter.out.BoardCollectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBoardCollectionQueryImpl implements GetBoardCollectionQuery {
    private final BoardCollectionJpaRepository boardCollectionJpaRepository;
    private final BoardCollectionDslRepository boardCollectionDslRepository;
    @Override
    public List<BoardCollectionEntity> getBoardCollectionEntities(){
        return boardCollectionJpaRepository.findAll();
    }

    @Override
    public List<BoardAggregationDto> getBoardAggregationDtos(){
        return boardCollectionDslRepository.getBoardAggregationDtos();
    }
}

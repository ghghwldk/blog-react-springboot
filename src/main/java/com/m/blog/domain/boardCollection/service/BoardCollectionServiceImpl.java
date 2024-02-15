package com.m.blog.domain.boardCollection.service;

import com.m.blog.domain.boardCollection.dto.BoardAggregationDto;
import com.m.blog.domain.boardCollection.entity.BoardCollection;
import com.m.blog.domain.boardCollection.repository.BoardCollectionDslRepository;
import com.m.blog.domain.boardCollection.repository.BoardCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCollectionServiceImpl implements BoardCollectionService{
    private final BoardCollectionRepository boardCollectionRepository;
    private final BoardCollectionDslRepository boardCollectionDslRepository;
    @Override
    public List<BoardCollection> getBoardCollections(){
        return boardCollectionRepository.findAll();
    }

    @Override
    public List<BoardAggregationDto> getBoardAggregationDtos(){
        return boardCollectionDslRepository.getBoardAggregationDtos();
    }
}

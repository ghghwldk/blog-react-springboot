package com.m.blog.domain.boardCollection.adapter.persistence;

import com.m.blog.domain.boardCollection.application.port.persistence.GetBoardCollectionPort;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardAggregationDto;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionDslRepository;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionEntity;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBoardCollectionAdapter implements GetBoardCollectionPort {
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

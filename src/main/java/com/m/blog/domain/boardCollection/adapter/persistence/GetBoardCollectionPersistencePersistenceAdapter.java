package com.m.blog.domain.boardCollection.adapter.persistence;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;
import com.m.blog.domain.boardCollection.application.port.persistence.GetBoardCollectionPersistencePort;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionDslRepository;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionEntityMapper;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetBoardCollectionPersistencePersistenceAdapter implements GetBoardCollectionPersistencePort {
    private final BoardCollectionJpaRepository boardCollectionJpaRepository;
    private final BoardCollectionDslRepository boardCollectionDslRepository;
    @Override
    public List<BoardCollection> getBoardCollections(){
        return boardCollectionJpaRepository.findAll().stream()
                .map(BoardCollectionEntityMapper::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardCollection.Aggregation> getAggregations(){
        return boardCollectionDslRepository.getAggregationDtos().stream()
                .map(BoardCollectionEntityMapper::of)
                .collect(Collectors.toList());
    }
}

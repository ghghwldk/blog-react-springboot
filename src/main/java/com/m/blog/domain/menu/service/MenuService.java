package com.m.blog.domain.menu.service;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;
import com.m.blog.domain.boardCollection.application.port.persistence.GetBoardCollectionPersistencePort;
import com.m.blog.domain.menu.application.usecase.MenuUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService implements MenuUsecase {
    private final GetBoardCollectionPersistencePort getBoardCollectionPersistencePort;

    @Override
    public List<BoardCollection.AggregationPerBoardCollection> get(){
        return BoardCollection.of(
                getBoardCollectionPersistencePort.getBoardCollections(),
                getBoardCollectionPersistencePort.getAggregations()
        );
    }
}

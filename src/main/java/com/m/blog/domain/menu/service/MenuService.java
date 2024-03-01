package com.m.blog.domain.menu.service;

import com.m.blog.domain.boardCollection.adapter.in.GetBoardCollectionQuery;
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;
import com.m.blog.domain.menu.application.usecase.MenuUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService implements MenuUsecase {
    private final GetBoardCollectionQuery getBoardCollectionQuery;

    @Override
    public MenuResponse get(){
        return MenuResponse.of(
                getBoardCollectionQuery.getBoardCollectionEntities(),
                getBoardCollectionQuery.getBoardAggregationDtos()
        );
    }
}

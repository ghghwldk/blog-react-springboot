package com.m.blog.domain.menu.service;

import com.m.blog.domain.boardCollection.adapter.in.GetBoardCollectionQuery;
import com.m.blog.domain.menu.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final GetBoardCollectionQuery getBoardCollectionQuery;

    @Override
    public MenuResponse get(){
        return MenuResponse.of(
                getBoardCollectionQuery.getBoardCollectionEntities(),
                getBoardCollectionQuery.getBoardAggregationDtos()
        );
    }
}

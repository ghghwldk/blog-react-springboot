package com.m.blog.boardCollection.application.service;

import com.m.blog.common.Query;
import com.m.blog.boardCollection.infrastructure.repository.BoardCollectionDslRepository;
import com.m.blog.boardCollection.infrastructure.repository.BoardCollectionJpaRepository;
import com.m.blog.boardCollection.application.query.MenuQuery;
import com.m.blog.boardCollection.infrastructure.web.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Query
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService implements MenuQuery {

    private final BoardCollectionJpaRepository boardCollectionJpaRepository;
    private final BoardCollectionDslRepository boardCollectionDslRepository;

    @Override
    public MenuResponse get(){
        return MenuResponse.of(
                boardCollectionJpaRepository.findAll()
                , boardCollectionDslRepository.getAggregationDtos()
        );
    }
}

package com.m.blog.domain.menu.service;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionDslRepository;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionJpaRepository;
import com.m.blog.domain.menu.application.query.MenuQuery;
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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

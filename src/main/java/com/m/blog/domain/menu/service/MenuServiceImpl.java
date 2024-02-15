package com.m.blog.domain.menu.service;

import com.m.blog.domain.boardCollection.service.BoardCollectionService;
import com.m.blog.domain.menu.dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final BoardCollectionService boardCollectionService;

    @Override
    public MenuResponseDto get(){
        return MenuResponseDto.of(
                boardCollectionService.getBoardCollections(),
                boardCollectionService.getBoardAggregationDtos()
        );
    }
}

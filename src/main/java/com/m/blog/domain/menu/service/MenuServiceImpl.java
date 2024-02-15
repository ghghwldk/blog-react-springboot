package com.m.blog.domain.menu.service;

import com.m.blog.domain.boardCollection.dto.BoardAggregationDto;
import com.m.blog.domain.boardCollection.entity.BoardCollection;
import com.m.blog.domain.boardCollection.service.BoardCollectionService;
import com.m.blog.domain.menu.dto.MenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final BoardCollectionService boardCollectionService;


    private MenuResponseDto get(List<BoardCollection> boardCollections,
                            List<BoardAggregationDto> boardAggregationDtos){
        List<MenuResponseDto.Nested> nesteds = new LinkedList<>();

        for(BoardCollection bc: boardCollections){
            int postingCount = 0;
            String boardCollectionName= bc.getName();
            int boardCollectionId= bc.getId();
            List<BoardAggregationDto> InSpecificBoardCollection = new LinkedList<>();
            for(BoardAggregationDto b: boardAggregationDtos){
                if(b.getBoardCollectionId() == boardCollectionId){
                    InSpecificBoardCollection.add(b);
                    postingCount += b.getPostingCount();
                }
            }
            nesteds.add(MenuResponseDto.Nested.builder()
                    .boardCollectionName(boardCollectionName)
                    .boardCollectionId(boardCollectionId)
                    .postingCount(postingCount)
                    .boardInformationInMenuDtos(InSpecificBoardCollection)
                    .build());
        }

        return MenuResponseDto.builder()
                .nesteds(nesteds)
                .build();
    }

    @Override
    public MenuResponseDto get(){
        return get(boardCollectionService.getBoardCollections(),
                boardCollectionService.getBoardAggregationDtos());
    }

}

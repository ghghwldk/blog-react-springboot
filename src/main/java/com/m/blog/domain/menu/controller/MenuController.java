package com.m.blog.domain.menu.controller;

import com.m.blog.domain.boardCollection.service.BoardCollectionService;
import com.m.blog.domain.menu.dto.MenuResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board_collection")
public class MenuController {
    @Autowired
    BoardCollectionService boardCollectionService;

    @GetMapping("/list/group_by_board_collection_id")
    MenuResponseDto getMenuVos (){
        return boardCollectionService.getMenuResponseDto();
    }
}

package com.m.blog.controller;

import com.m.blog.service.BoardCollectionService;
import com.m.blog.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board_collection")
public class BoardCollectionController {
    @Autowired
    BoardCollectionService boardCollectionService;

    @GetMapping("/list/group_by_board_collection_id")
    List<MenuVo> getMenuVos (){
        return boardCollectionService.getMenuResponseDto();
    }
}

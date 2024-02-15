package com.m.blog.domain.menu.controller;

import com.m.blog.domain.boardCollection.service.BoardCollectionServiceImpl;
import com.m.blog.domain.menu.dto.MenuResponseDto;
import com.m.blog.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    MenuResponseDto get (){
        return menuService.get();
    }
}


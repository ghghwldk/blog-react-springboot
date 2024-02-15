package com.m.blog.domain.menu.controller;

import com.m.blog.domain.menu.dto.MenuResponse;
import com.m.blog.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    MenuResponse get (){
        return menuService.get();
    }
}


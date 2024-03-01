package com.m.blog.domain.menu.infrastructure.web.controller;

import com.m.blog.domain.menu.application.usecase.MenuUsecase;
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPagingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuUsecase menuUsecase;

    @GetMapping
    MenuResponse get (){
        return menuUsecase.get();
    }
}


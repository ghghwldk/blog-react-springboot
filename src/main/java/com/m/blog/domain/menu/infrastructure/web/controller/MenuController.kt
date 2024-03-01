package com.m.blog.domain.menu.infrastructure.web.controller

import com.m.blog.domain.menu.application.usecase.MenuUsecase
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
class MenuController(val menuUsecase: MenuUsecase) {
    @GetMapping
    fun get():MenuResponse
        =  menuUsecase.get()
}
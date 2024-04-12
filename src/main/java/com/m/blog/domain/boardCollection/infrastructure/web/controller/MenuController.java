package com.m.blog.domain.boardCollection.infrastructure.web.controller;

import com.m.blog.domain.boardCollection.application.port.entrypoint.api.MenuEndpointPort;
import com.m.blog.domain.boardCollection.infrastructure.web.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuEndpointPort menuEndpointPort;

    @GetMapping
    MenuResponse get (){
        return menuEndpointPort.get();
    }
}


package com.m.blog.aggregate.boardCollection.infrastructure.web.controller;

import com.m.blog.aggregate.boardCollection.application.port.in.web.MenuEndpointPort;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuEndpointPort menuEndpointPort;

    @GetMapping
    ResponseEntity<MenuResponse> get (){
        return ResponseEntity.ok(menuEndpointPort.get());
    }
}


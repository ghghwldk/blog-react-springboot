package com.m.blog.aggregate.boardCollection.adapter.in.web;

import com.m.blog.aggregate.boardCollection.application.query.MenuQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuQuery menuQuery;

    @GetMapping
    ResponseEntity<MenuResponse> get (){
        return ResponseEntity.ok(menuQuery.get());
    }
}


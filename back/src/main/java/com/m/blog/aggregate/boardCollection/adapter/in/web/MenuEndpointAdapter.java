package com.m.blog.aggregate.boardCollection.adapter.in.web;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.boardCollection.application.port.in.web.MenuEndpointPort;
import com.m.blog.aggregate.boardCollection.application.query.MenuQuery;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.MenuResponse;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class MenuEndpointAdapter implements MenuEndpointPort {
    private final MenuQuery menuQuery;

    @Override
    public MenuResponse get() {
        return menuQuery.get();
    }
}

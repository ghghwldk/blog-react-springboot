package com.m.blog.aggregate.boardCollection.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.aggregate.boardCollection.application.port.entrypoint.api.MenuEndpointPort;
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

package com.m.blog.boardCollection.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.boardCollection.application.port.entrypoint.api.MenuEndpointPort;
import com.m.blog.boardCollection.application.query.MenuQuery;
import com.m.blog.boardCollection.infrastructure.web.dto.MenuResponse;
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

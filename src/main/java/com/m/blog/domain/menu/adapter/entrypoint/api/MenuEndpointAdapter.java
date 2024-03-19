package com.m.blog.domain.menu.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.menu.application.port.entrypoint.api.MenuEndpointPort;
import com.m.blog.domain.menu.application.query.MenuQuery;
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;
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

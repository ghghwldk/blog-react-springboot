package com.m.blog.domain.menu.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.boardCollection.application.domain.BoardCollection;
import com.m.blog.domain.menu.application.port.entrypoint.api.FindMenuEndpointPort;
import com.m.blog.domain.menu.application.usecase.MenuUsecase;
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adapter
@RequiredArgsConstructor
public class FindMenuEndpointAdapter implements FindMenuEndpointPort {
    private final MenuUsecase menuUsecase;

    @Override
    public MenuResponse get() {
        return MenuMapper.of(menuUsecase.get());
    }
}

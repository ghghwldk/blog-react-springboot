package com.m.blog.domain.boardCollection.application.port.entrypoint.api;

import com.m.blog.domain.boardCollection.infrastructure.web.dto.MenuResponse;

public interface MenuEndpointPort {
    MenuResponse get ();
}

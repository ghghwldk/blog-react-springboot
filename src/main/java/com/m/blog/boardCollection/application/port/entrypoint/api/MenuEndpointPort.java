package com.m.blog.boardCollection.application.port.entrypoint.api;

import com.m.blog.boardCollection.infrastructure.web.dto.MenuResponse;

public interface MenuEndpointPort {
    MenuResponse get ();
}

package com.m.blog.aggregate.boardCollection.application.port.entrypoint.api;

import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.MenuResponse;

public interface MenuEndpointPort {
    MenuResponse get ();
}

package com.m.blog.domain.menu.application.port.entrypoint.api;

import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;

public interface MenuEndpointPort {
    MenuResponse get ();
}

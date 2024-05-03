package com.m.blog.aggregate.boardCollection.application.port.in.web;

import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.MenuResponse;

public interface MenuEndpointPort {
    MenuResponse get ();
}

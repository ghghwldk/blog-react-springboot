package com.m.blog.aggregate.boardCollection.application.port.in;

import com.m.blog.aggregate.boardCollection.adapter.in.web.MenuResponse;

public interface MenuQuery {
    MenuResponse get();
}

package com.m.blog.domain.menu.application.query;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;

import java.util.List;

public interface MenuQuery {
    MenuResponse get();
}

package com.m.blog.domain.menu.service;

import com.m.blog.domain.menu.dto.MenuResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface MenuService {
    MenuResponseDto get();
}

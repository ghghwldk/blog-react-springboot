package com.m.blog.domain.menu.application.usecase;

import com.m.blog.domain.boardCollection.application.domain.BoardCollection;

import java.util.List;

public interface MenuUsecase {
    List<BoardCollection.AggregationPerBoardCollection> get();
}

package com.m.blog.vo;

import com.m.blog.entity.Board;

import java.util.List;

public class MenuVo {
    String boardCollectionName;
    int boardCollectionId;
    List<Board> boards;

    public MenuVo(String boardCollectionName, int boardCollectionId, List<Board> boards) {
        this.boardCollectionName = boardCollectionName;
        this.boardCollectionId = boardCollectionId;
        this.boards = boards;
    }
}

package com.m.blog.vo;

import com.m.blog.dto.BoardInformationInMenuDto;
import com.m.blog.entity.Board;

import java.util.List;

public class MenuVo {
    String boardCollectionName;
    int boardCollectionId;
    int postingCount;
    List<BoardInformationInMenuDto> boardInformationInMenuDtos;

    public MenuVo(String boardCollectionName, int boardCollectionId, int postingCount, List<BoardInformationInMenuDto> boardInformationInMenuDtos) {
        this.boardCollectionName = boardCollectionName;
        this.boardCollectionId = boardCollectionId;
        this.postingCount = postingCount;
        this.boardInformationInMenuDtos = boardInformationInMenuDtos;
    }
}

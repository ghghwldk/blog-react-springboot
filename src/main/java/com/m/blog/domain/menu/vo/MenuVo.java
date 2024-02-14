package com.m.blog.domain.menu.vo;

import com.m.blog.domain.boardCollection.dto.BoardInformationInMenuDto;

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

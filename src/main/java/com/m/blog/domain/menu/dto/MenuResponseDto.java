package com.m.blog.domain.menu.dto;

import com.m.blog.domain.boardCollection.dto.BoardInformationInMenuDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MenuResponseDto {
    List<Nested> nesteds;

    @Data
    @Builder
    @AllArgsConstructor
    public static class Nested {
        String boardCollectionName;
        int boardCollectionId;
        int postingCount;
        List<BoardInformationInMenuDto> boardInformationInMenuDtos;
    }

}

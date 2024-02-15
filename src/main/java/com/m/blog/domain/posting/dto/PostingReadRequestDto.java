package com.m.blog.domain.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostingReadRequestDto {
    private int boardCollectionId;
    private int boardId;
    private int id;
}

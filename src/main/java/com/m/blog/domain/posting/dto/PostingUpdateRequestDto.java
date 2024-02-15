package com.m.blog.domain.posting.dto;

import lombok.Data;

@Data
public class PostingUpdateRequestDto {
    private String markup;
    private String title;
    private int boardCollectionId;
    private int boardId;
    private int postingId;
}

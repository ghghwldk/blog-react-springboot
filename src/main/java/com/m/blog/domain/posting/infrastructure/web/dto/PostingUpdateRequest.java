package com.m.blog.domain.posting.infrastructure.web.dto;

import lombok.Data;

@Data
public class PostingUpdateRequest {
    private String markup;
    private String title;
    private int boardCollectionId;
    private int boardId;
    private int postingId;
}

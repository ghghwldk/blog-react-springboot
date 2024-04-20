package com.m.blog.aggregate.boardCollection.infrastructure.web.dto;

import lombok.Data;

@Data
public class PostingCreateRequest {
    private String boardId;
    private String title;
    private String content;
}


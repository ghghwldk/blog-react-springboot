package com.m.blog.boardCollection.infrastructure.web.dto;

import lombok.Data;

@Data
public class PostingCreateRequest {
    private String boardId;
    private String title;
    private String content;
}


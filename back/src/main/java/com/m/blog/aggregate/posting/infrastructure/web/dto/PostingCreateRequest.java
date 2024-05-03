package com.m.blog.aggregate.posting.infrastructure.web.dto;

import lombok.Data;

@Data
public class PostingCreateRequest {
    private String boardId;
    private String title;
    private String content;
}


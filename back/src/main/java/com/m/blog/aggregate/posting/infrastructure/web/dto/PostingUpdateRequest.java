package com.m.blog.aggregate.posting.infrastructure.web.dto;

import lombok.Data;

@Data
public class PostingUpdateRequest {
    private String markup;
    private String title;
    private String boardId;
    private String postingId;
}

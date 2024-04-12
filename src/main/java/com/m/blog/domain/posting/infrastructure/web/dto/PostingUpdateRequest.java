package com.m.blog.domain.posting.infrastructure.web.dto;

import lombok.Data;

@Data
public class PostingUpdateRequest {
    private String markup;
    private String title;
    private String postingId;
}

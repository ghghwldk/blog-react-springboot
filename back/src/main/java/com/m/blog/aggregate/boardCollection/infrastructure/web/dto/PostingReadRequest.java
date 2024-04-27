package com.m.blog.aggregate.boardCollection.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostingReadRequest {
    private String id;
}

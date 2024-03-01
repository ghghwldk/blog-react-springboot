package com.m.blog.domain.posting.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostingReadRequest {
    private int boardCollectionId;
    private int boardId;
    private int id;
}

package com.m.blog.domain.posting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@Builder
public class PostingReadFilteredPagingRequest {
    private int boardCollectionId;
    private int boardId;
    private Pageable pageable;
}

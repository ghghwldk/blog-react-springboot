package com.m.blog.boardCollection.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@Builder
public class PostingReadPagingRequest {
    private Pageable pageable;
}

package com.m.blog.global.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PagingResponse {

    private Object content;
    private Integer totalPages;
    private Integer totalElements;
    private String location;
}

package com.m.blog.global.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PagingResponse {

    Object content= null;
    Integer totalPages= null;
    Integer totalElements = null;
    String location = "";

    public PagingResponse(Object content, Integer totalPages, Integer totalElements, String location) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.location = location;
    }
}

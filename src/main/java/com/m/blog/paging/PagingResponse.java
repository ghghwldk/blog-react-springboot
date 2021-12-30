package com.m.blog.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingResponse {

    Object content= null;
    Integer totalPage= null;
    public PagingResponse(Integer totalPage, Object content) {
        this.totalPage = totalPage;
        this.content= content;
    }
}

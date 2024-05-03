package com.m.blog.aggregate.posting.application.port.in.web;

import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingReadPerBoardPagingRequest;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingReadRequest;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;

public interface FindPositngEndpointPort {
    PagingResponse getPagingResponse(PostingReadPerBoardPagingRequest requestDto);
    PagingResponse getPagingResponse(PostingReadPagingRequest requestDto);
    PostingReadResponse get(PostingReadRequest requestDto);
}

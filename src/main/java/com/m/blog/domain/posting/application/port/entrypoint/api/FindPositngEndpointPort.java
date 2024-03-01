package com.m.blog.domain.posting.application.port.entrypoint.api;

import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadFilteredPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;

public interface FindPositngEndpointPort {
    PagingResponse getPagingResponse(PostingReadFilteredPagingRequest requestDto);
    PagingResponse getPagingResponse(PostingReadPagingRequest requestDto);
    PostingReadResponse get(PostingReadRequest requestDto);
}

package com.m.blog.domain.posting.service;

import com.m.blog.domain.posting.dto.*;
import com.m.blog.global.paging.PagingResponse;

public interface PostingService {
    PagingResponse getPagingResponse(PostingReadFilteredPagingRequest requestDto);
    PagingResponse getPagingResponse(PostingReadPagingRequest requestDto);
    void update(PostingUpdateRequest requestDto);
    PostingReadResponse get(PostingReadRequest requestDto);
    void create(PostingCreateRequest requestDto);
}

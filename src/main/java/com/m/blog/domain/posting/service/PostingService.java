package com.m.blog.domain.posting.service;

import com.m.blog.domain.posting.dto.*;
import com.m.blog.global.paging.PagingResponse;

import javax.transaction.Transactional;

public interface PostingService {
    PagingResponse getPagingResponse(PostingReadFilteredPagingRequestDto requestDto);
    PagingResponse getPagingResponse(PostingReadPagingRequestDto requestDto);
    void update(PostingUpdateRequestDto requestDto);
    PostingReadResponseDto get(PostingReadRequestDto requestDto);
    void create(PostingCreateRequestDto requestDto);
}

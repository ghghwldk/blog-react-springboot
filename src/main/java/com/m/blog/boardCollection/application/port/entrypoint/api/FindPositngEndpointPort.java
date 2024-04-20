package com.m.blog.boardCollection.application.port.entrypoint.api;

import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadPerBoardPagingRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadResponse;
import com.m.blog.global.paging.PagingResponse;

public interface FindPositngEndpointPort {
    PagingResponse getPagingResponse(PostingReadPerBoardPagingRequest requestDto);
    PagingResponse getPagingResponse(PostingReadPagingRequest requestDto);
    PostingReadResponse get(PostingReadRequest requestDto);
}

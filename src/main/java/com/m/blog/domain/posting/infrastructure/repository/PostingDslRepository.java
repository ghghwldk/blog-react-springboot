package com.m.blog.domain.posting.infrastructure.repository;

import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadFilteredPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadRequest;
import org.springframework.data.domain.Page;

public interface PostingDslRepository {
    int findNewId(int boardCollectionId, int boardId);
    Page<PostingDto> get(PostingReadPagingRequest requestDto);
    Page<PostingDto> get(PostingReadFilteredPagingRequest requestDto);
    PostingDto get(PostingReadRequest requestDto);


}

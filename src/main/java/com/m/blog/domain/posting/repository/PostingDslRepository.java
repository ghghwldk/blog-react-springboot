package com.m.blog.domain.posting.repository;

import com.m.blog.domain.posting.dto.PostingReadFilteredPagingRequest;
import com.m.blog.domain.posting.dto.PostingReadPagingRequest;
import com.m.blog.domain.posting.dto.PostingReadRequest;
import com.m.blog.domain.posting.dto.dsl.PostingDto;
import org.springframework.data.domain.Page;

public interface PostingDslRepository {
    int findNewId(int boardCollectionId, int boardId);
    Page<PostingDto> get(PostingReadPagingRequest requestDto);
    Page<PostingDto> get(PostingReadFilteredPagingRequest requestDto);
    PostingDto get(PostingReadRequest requestDto);


}

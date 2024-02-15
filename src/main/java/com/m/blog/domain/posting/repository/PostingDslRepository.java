package com.m.blog.domain.posting.repository;

import com.m.blog.domain.posting.dto.PostingReadFilteredPagingRequestDto;
import com.m.blog.domain.posting.dto.PostingReadPagingRequestDto;
import com.m.blog.domain.posting.dto.PostingReadRequestDto;
import com.m.blog.domain.posting.dto.dsl.PostingDto;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

public interface PostingDslRepository {
    int findNewId(int boardCollectionId, int boardId);
    Page<PostingDto> get(PostingReadPagingRequestDto requestDto);
    Page<PostingDto> get(PostingReadFilteredPagingRequestDto requestDto);
    PostingDto get(PostingReadRequestDto requestDto);


}

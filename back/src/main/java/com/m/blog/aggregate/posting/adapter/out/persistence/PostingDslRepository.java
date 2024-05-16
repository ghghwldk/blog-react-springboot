package com.m.blog.aggregate.posting.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostingDslRepository {
    Page<PostingDto> getPage(Pageable pageable);

    Page<PostingDto> getPagePerBoard(String boardId, Pageable pageable);
    PostingDto getSinglePage(String condition);
}

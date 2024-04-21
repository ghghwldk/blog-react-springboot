package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostingDslRepository {
    Page<PostingDto> getPage(Pageable pageable);

    Page<PostingDto> getPagePerBoard(String boardId, Pageable pageable);
    PostingDto getSinglePage(Posting.PostingId condition);
}

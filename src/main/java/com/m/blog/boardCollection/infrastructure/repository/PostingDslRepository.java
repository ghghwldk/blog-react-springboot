package com.m.blog.boardCollection.infrastructure.repository;

import com.m.blog.boardCollection.application.domain.Posting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostingDslRepository {
    Page<PostingDto> getPage(Pageable pageable);

    Page<PostingDto> getPagePerBoard(String boardId, Pageable pageable);
    PostingDto getSinglePage(Posting.PostingId condition);
}

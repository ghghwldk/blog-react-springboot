package com.m.blog.domain.posting.infrastructure.repository;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadFilteredPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostingDslRepository {
    int findNewId(int boardCollectionId, int boardId);
    Page<PostingDto> getPage(Pageable pageable);

    Page<PostingDto> getFiltered(int boardCollectionIdm, int boardId, Pageable pageable);
    PostingDto getSingle(Posting.PostingId condition);


}

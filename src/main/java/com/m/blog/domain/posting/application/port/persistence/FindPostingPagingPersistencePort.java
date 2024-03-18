package com.m.blog.domain.posting.application.port.persistence;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.repository.PostingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindPostingPagingPersistencePort {

    Page<PostingDto> getPaging(Pageable pageable);

//    Page<PostingDto> get(PostingReadFilteredPagingRequest requestDto);
    Page<PostingDto> getFilteredPage(Posting.InBoardCondition condition, Pageable pageable);
}

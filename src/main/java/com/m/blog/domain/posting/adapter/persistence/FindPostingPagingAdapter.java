package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPagingPort;
import com.m.blog.domain.posting.infrastructure.repository.PostingDslRepository;
import com.m.blog.domain.posting.infrastructure.repository.PostingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Adapter
@RequiredArgsConstructor
class FindPostingPagingAdapter implements FindPostingPagingPort {
    private final PostingDslRepository postingDslRepository;

    @Override
    public Page<PostingDto> getPaging(Pageable pageable) {
        return postingDslRepository.getPage(pageable);
    }

    @Override
    public Page<PostingDto> getFilteredPage(Posting.InBoardCondition condition, Pageable pageable) {
        return postingDslRepository
                .getFiltered(condition.getBoardCollectionId(), condition.getBoardId(), pageable);
    }
}

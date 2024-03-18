package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPagingPersistencePort;
import com.m.blog.domain.posting.infrastructure.repository.PostingDslRepository;
import com.m.blog.domain.posting.infrastructure.repository.PostingDto;
import com.m.blog.domain.posting.infrastructure.repository.PostingPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Adapter
@RequiredArgsConstructor
class FindPostingPagingPersistenceAdapter implements FindPostingPagingPersistencePort {
    private final PostingDslRepository postingDslRepository;

    @Override
    public Page<Posting.Sophisticated> getPaging(Pageable pageable) {
        return postingDslRepository.getPage(pageable).map(PostingPersistenceMapper::of);
    }

    @Override
    public Page<Posting.Sophisticated> getFilteredPage(Posting.InBoardCondition condition, Pageable pageable) {
        return postingDslRepository
                .getFiltered(condition.getBoardCollectionId(), condition.getBoardId(), pageable)
                .map(PostingPersistenceMapper::of);
    }
}

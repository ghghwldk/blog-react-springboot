package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPersistencePort;
import com.m.blog.domain.posting.infrastructure.repository.PostingDslRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class FindPostingPersistenceAdapter implements FindPostingPersistencePort {
    private final PostingDslRepository postingDslRepository;

    @Override
    public Posting.Sophisticated get(Posting.PostingId condition) {
        return PostingJpaMapper.of(postingDslRepository.getSingle(condition));
    }
}

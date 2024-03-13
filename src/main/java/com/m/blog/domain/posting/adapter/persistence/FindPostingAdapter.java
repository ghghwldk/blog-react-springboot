package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingPort;
import com.m.blog.domain.posting.infrastructure.repository.PostingDslRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class FindPostingAdapter implements FindPostingPort {
    private final PostingDslRepository postingDslRepository;

    @Override
    public Posting.Sophisticated get(Posting.PostingId condition) {
        return PostingJpaMapper.of(postingDslRepository.getSingle(condition));
    }
}

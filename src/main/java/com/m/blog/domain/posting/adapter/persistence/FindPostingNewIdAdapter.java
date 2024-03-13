package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingNewIdPort;
import com.m.blog.domain.posting.infrastructure.repository.PostingDslRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class FindPostingNewIdAdapter implements FindPostingNewIdPort {
    private final PostingDslRepository postingDslRepository;

    @Override
    public Posting.NewId findNewId(Posting.IdWithoutPostingId id) {
        return Posting.getNextId(
            postingDslRepository.findMaxId(id.getBoardCollectionId(), id.getBoardId())
        );
    }
}

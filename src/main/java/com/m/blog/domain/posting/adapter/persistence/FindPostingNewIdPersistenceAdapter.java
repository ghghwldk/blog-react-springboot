package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingNewIdPersistencePort;
import com.m.blog.domain.posting.infrastructure.repository.PostingDslRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class FindPostingNewIdPersistenceAdapter implements FindPostingNewIdPersistencePort {
    private final PostingDslRepository postingDslRepository;

    @Override
    public Posting.NewId findNewId(Posting.IdWithoutPostingId id) {
        return Posting.getNextId(
            postingDslRepository.findMaxId(id.getBoardCollectionId(), id.getBoardId())
        );
    }
}

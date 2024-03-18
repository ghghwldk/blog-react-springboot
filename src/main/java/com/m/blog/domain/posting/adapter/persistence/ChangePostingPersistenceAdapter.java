package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.ChangePostingPersistencePort;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;
import com.m.blog.domain.posting.infrastructure.repository.PostingJpaRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class ChangePostingPersistenceAdapter implements ChangePostingPersistencePort {
    private final PostingJpaRepository postingJpaRepository;

    @Override
    public void update(Posting.PostingId id, Posting.Mutable target) {
        PostingEntity found = postingJpaRepository
                .findByBoardCollectionIdAndBoardIdAndId(
                        id.getBoardCollectionId(),
                        id.getBoardId(),
                        id.getPostingId()
                ).orElseThrow(RuntimeException::new);

        found.setContent(target.getContent());
        found.setTitle(target.getTitle());
    }
}

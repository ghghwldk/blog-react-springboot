package com.m.blog.boardCollection.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.application.port.persistence.ChangePostingPersistencePort;
import com.m.blog.boardCollection.infrastructure.repository.PostingEntity;
import com.m.blog.boardCollection.infrastructure.repository.PostingJpaRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class ChangePostingPersistenceAdapter implements ChangePostingPersistencePort {
    private final PostingJpaRepository postingJpaRepository;

    @Override
    public void update(Posting after) {
        PostingEntity found = postingJpaRepository
                .findById(after.getPostingId().getValue()).orElseThrow(RuntimeException::new);

        found.setContent(after.getContent());
        found.setTitle(after.getTitle());
    }
}

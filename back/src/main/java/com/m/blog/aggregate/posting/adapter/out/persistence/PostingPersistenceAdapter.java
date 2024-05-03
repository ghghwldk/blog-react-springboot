package com.m.blog.aggregate.posting.adapter.out.persistence;

import com.m.blog.aggregate.posting.infrastructure.repository.PostingJpaRepository;
import com.m.blog.aggregate.posting.application.port.out.persistence.ChangePostingPersistencePort;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.aggregate.boardCollection.application.port.out.persistence.SavePostingPersistencePort;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class PostingPersistenceAdapter implements SavePostingPersistencePort, ChangePostingPersistencePort {
    private final PostingJpaRepository postingJpaRepository;

    @Override
    public void save(Posting posting) {
        postingJpaRepository.save(PostingPersistenceMapper.toEntity(posting));
    }

    @Override
    public void update(Posting after) {
        postingJpaRepository.save(PostingPersistenceMapper.toEntity(after));
    }
}

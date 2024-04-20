package com.m.blog.aggregate.boardCollection.adapter.persistence;

import com.m.blog.aggregate.boardCollection.infrastructure.repository.PostingJpaRepository;
import com.m.blog.aggregate.boardCollection.application.domain.BoardCollection;
import com.m.blog.aggregate.boardCollection.application.port.persistence.ChangePostingPersistencePort;
import com.m.blog.common.Adapter;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.aggregate.boardCollection.application.port.persistence.SavePostingPersistencePort;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class PostingPersistenceAdapter implements SavePostingPersistencePort, ChangePostingPersistencePort {
    private final PostingJpaRepository postingJpaRepository;

    @Override
    public void save(Posting posting) {
        postingJpaRepository.save(com.m.blog.aggregate.boardCollection.adapter.persistence.PostingJpaMapper.toEntity(posting));
    }

    @Override
    public void update(Posting after) {
        postingJpaRepository.save(com.m.blog.aggregate.boardCollection.adapter.persistence.PostingJpaMapper.toEntity(after));
    }
}

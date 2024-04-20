package com.m.blog.boardCollection.adapter.persistence;

import com.m.blog.boardCollection.application.domain.BoardCollection;
import com.m.blog.boardCollection.application.port.persistence.ChangePostingPersistencePort;
import com.m.blog.boardCollection.infrastructure.repository.PostingEntity;
import com.m.blog.common.Adapter;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.application.port.persistence.SavePostingPersistencePort;
import com.m.blog.boardCollection.infrastructure.repository.PostingJpaRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class PostingPersistenceAdapter implements SavePostingPersistencePort, ChangePostingPersistencePort {
    private final PostingJpaRepository postingJpaRepository;

    @Override
    public void save(Posting posting) {
        postingJpaRepository.save(PostingJpaMapper.toEntity(posting));
    }

    @Override
    public void update(Posting after) {
        postingJpaRepository.save(PostingJpaMapper.toEntity(after));
    }
}

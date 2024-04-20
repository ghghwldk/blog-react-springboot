package com.m.blog.boardCollection.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.application.port.persistence.SavePostingPersistencePort;
import com.m.blog.boardCollection.infrastructure.repository.PostingJpaRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class SavePostingPersistenceAdapter implements SavePostingPersistencePort {
    private final PostingJpaRepository postingJpaRepository;

    @Override
    public void save(Posting posting) { postingJpaRepository.save(PostingJpaMapper.toEntity(posting));}
}

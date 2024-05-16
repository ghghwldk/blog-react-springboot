package com.m.blog.aggregate.posting.adapter.out.persistence;

import com.m.blog.aggregate.posting.application.port.out.persistence.LoadPostingPersistencePort;
import com.m.blog.aggregate.posting.application.port.out.persistence.ChangePostingPersistencePort;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.application.port.out.persistence.SavePostingPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
class PostingPersistenceAdapter
        implements SavePostingPersistencePort, ChangePostingPersistencePort, LoadPostingPersistencePort {
    private final PostingJpaRepository postingJpaRepository;

    @Override
    public void save(Posting posting) {
        postingJpaRepository.save(PostingPersistenceMapper.toEntity(posting));
    }

    @Override
    public void update(Posting after) {
        postingJpaRepository.save(PostingPersistenceMapper.toEntity(after));
    }

    @Override
    public Optional<Posting> load(String postingId) {
        return postingJpaRepository.findById(postingId)
                .map(PostingPersistenceMapper::of);
    }
}

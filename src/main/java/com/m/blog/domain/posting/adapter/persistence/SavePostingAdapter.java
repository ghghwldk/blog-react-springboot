package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.SavePostingPort;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;
import com.m.blog.domain.posting.infrastructure.repository.PostingJpaRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class SavePostingAdapter implements SavePostingPort {
    private final PostingJpaRepository postingJpaRepository;

    @Override
    public void save(Posting.PostingId id, Posting.Mutable target) {
        postingJpaRepository.save(
                PostingEntity.builder()
                        .id(id.getPostingId())
                        .boardId(id.getBoardId())
                        .boardCollectionId(id.getBoardCollectionId())
                        .title(target.getTitle())
                        .content(target.getContent())
                        .build()
        );
    }
}

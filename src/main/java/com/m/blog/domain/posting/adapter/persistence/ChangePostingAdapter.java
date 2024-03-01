package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.ChangePostingPort;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;
import com.m.blog.domain.posting.infrastructure.repository.PostingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@RequiredArgsConstructor
public class ChangePostingAdapter implements ChangePostingPort {
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

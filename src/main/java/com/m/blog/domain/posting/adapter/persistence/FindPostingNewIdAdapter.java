package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.port.persistence.FindPostingNewIdPort;
import com.m.blog.domain.posting.infrastructure.repository.PostingDslRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class FindPostingNewIdAdapter implements FindPostingNewIdPort {
    private final PostingDslRepository postingDslRepository;

    @Override
    public int findNewId(int boardCollectionId, int boardId) {
        return postingDslRepository.findNewId(boardCollectionId, boardId);
    }
}

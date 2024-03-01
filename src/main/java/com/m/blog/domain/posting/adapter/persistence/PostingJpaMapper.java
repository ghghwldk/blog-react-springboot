package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;

public class PostingJpaMapper {
    public PostingEntity toEntity (Posting domain){
        return PostingEntity.builder()
                .boardCollectionId(domain.getId().getBoardCollectionId())
                .boardId(domain.getId().getBoardId())
                .id(domain.getId().getPostingId())
                .title(domain.getTitle())
                .content(domain.getContent())
                .build();
    }
}

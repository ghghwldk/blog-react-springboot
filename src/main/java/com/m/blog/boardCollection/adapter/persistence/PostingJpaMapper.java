package com.m.blog.boardCollection.adapter.persistence;

import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.infrastructure.repository.PostingEntity;

class PostingJpaMapper {
    public static PostingEntity toEntity (Posting domain){
        return PostingEntity.builder()
                .id(domain.getPostingId().getValue())
                .title(domain.getTitle())
                .content(domain.getContent())
                .build();
    }


}

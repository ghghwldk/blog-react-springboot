package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;

public class PostingJpaMapper {
    public static PostingEntity toEntity (Posting domain){
        return PostingEntity.builder()
                .id(domain.getPostingId().getValue())
                .title(domain.getTitle())
                .content(domain.getContent())
                .build();
    }


}

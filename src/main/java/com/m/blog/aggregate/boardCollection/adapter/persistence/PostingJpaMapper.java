package com.m.blog.aggregate.boardCollection.adapter.persistence;

import com.m.blog.aggregate.boardCollection.infrastructure.repository.PostingEntity;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.common.Mapper;

@Mapper
class PostingJpaMapper {
    public static PostingEntity toEntity (Posting domain){
        return PostingEntity.builder()
                .id(domain.getPostingId().getValue())
                .title(domain.getTitle())
                .content(domain.getContent())
                .build();
    }


}

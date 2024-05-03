package com.m.blog.aggregate.posting.adapter.out.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.aggregate.posting.infrastructure.repository.PostingEntity;

public class PostingPersistenceMapper {
    public static PostingEntity toEntity (Posting domain){
        return PostingEntity.builder()
                .id(domain.getPostingId().getValue())
                .boardId(domain.getBoardId().getValue())
                .title(domain.getTitle())
                .content(domain.getContent())
                .build();
    }


    public static Posting of(PostingEntity entity){
        return new Posting(entity.getId(), entity.getBoardId(),
                entity.getTitle(), entity.getContent());
    }
}

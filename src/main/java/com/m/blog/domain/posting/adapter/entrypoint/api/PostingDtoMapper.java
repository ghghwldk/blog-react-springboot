package com.m.blog.domain.posting.adapter.entrypoint.api;

import com.m.blog.common.Mapper;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;

@Mapper
public class PostingDtoMapper {
    public Posting toDomain(PostingEntity entity){
        return Posting.withId(entity.getId(),
                entity.getBoardCollectionId(),
                entity.getBoardId());
    }
}

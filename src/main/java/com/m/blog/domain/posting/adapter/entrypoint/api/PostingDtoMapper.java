package com.m.blog.domain.posting.adapter.entrypoint.api;

import com.m.blog.common.Mapper;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingUpdateRequest;

@Mapper
public class PostingDtoMapper {
    public Posting toDomain(PostingEntity entity){
        return Posting.withId(entity.getId(),
                entity.getBoardCollectionId(),
                entity.getBoardId(),
                entity.getTitle(),
                entity.getContent());
    }

    public Posting toDomain(PostingUpdateRequest request){
        return Posting.withId(request.getPostingId(),
                request.getBoardCollectionId(),
                request.getBoardId(),
                request.getTitle(),
                request.getMarkup());
    }
}

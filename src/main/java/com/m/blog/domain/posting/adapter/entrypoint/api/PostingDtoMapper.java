package com.m.blog.domain.posting.adapter.entrypoint.api;

import com.m.blog.common.Mapper;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadFilteredPagingRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingUpdateRequest;

@Mapper
class PostingDtoMapper {
    public Posting toDomain(PostingEntity entity){
        return Posting.withId(entity.getId(),
                entity.getBoardCollectionId(),
                entity.getBoardId(),
                entity.getTitle(),
                entity.getContent());
    }

    public Posting.PostingId toId(PostingUpdateRequest request){
        return Posting.PostingId.builder()
                .boardCollectionId(request.getBoardCollectionId())
                .boardId(request.getBoardId())
                .postingId(request.getPostingId())
                .build();
    }

    public Posting.IdWithoutPostingId toId(PostingCreateRequest request){
        return Posting.IdWithoutPostingId.builder()
                .boardCollectionId(request.getBoardCollectionId())
                .boardId(request.getBoardId())
                .build();
    }

    public Posting.Mutable toMutable(PostingUpdateRequest request){
        return Posting.Mutable.builder()
                .content(request.getMarkup())
                .title(request.getTitle())
                .build();
    }
    public Posting.IdWithoutPostingId of(PostingReadFilteredPagingRequest request){
        return Posting.IdWithoutPostingId.builder()
                .boardCollectionId(request.getBoardCollectionId())
                .boardId(request.getBoardId())
                .build();
    }

    public Posting.Mutable toMutable(PostingCreateRequest request){
        return Posting.Mutable.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .build();
    }

    public Posting toDomain(PostingUpdateRequest request){
        return Posting.withId(request.getPostingId(),
                request.getBoardCollectionId(),
                request.getBoardId(),
                request.getTitle(),
                request.getMarkup());
    }
}

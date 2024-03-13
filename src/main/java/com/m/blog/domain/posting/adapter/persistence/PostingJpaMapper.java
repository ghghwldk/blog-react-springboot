package com.m.blog.domain.posting.adapter.persistence;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.repository.PostingDto;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;

import java.time.LocalDateTime;

class PostingJpaMapper {
    public PostingEntity toEntity (Posting domain){
        return PostingEntity.builder()
                .boardCollectionId(domain.getId().getBoardCollectionId())
                .boardId(domain.getId().getBoardId())
                .id(domain.getId().getPostingId())
                .title(domain.getTitle())
                .content(domain.getContent())
                .build();
    }

    public static Posting.Sophisticated of(PostingDto dto){
        return Posting.Sophisticated.builder()
                .postingId(dto.getPostingId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .boardId(dto.getBoardId())
                .boardName(dto.getBoardName())
                .boardCollectionId(dto.getBoardCollectionId())
                .boardCollectionName(dto.getBoardCollectionName())
                .createdTime(dto.getCreatedTime())
                .build();
    }
}

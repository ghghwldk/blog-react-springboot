package com.m.blog.domain.posting.infrastructure.repository;

import com.m.blog.domain.posting.application.domain.Posting;

public class PostingPersistenceMapper {
    public static Posting.Sophisticated of(PostingDto before){
        return Posting.Sophisticated.builder()
                .postingId(before.getPostingId())
                .title(before.getTitle())
                .content(before.getContent())
                .boardId(before.getBoardId())
                .boardName(before.getBoardName())
                .boardCollectionId(before.getBoardCollectionId())
                .boardCollectionName(before.getBoardCollectionName())
                .createdTime(before.getCreatedTime())
                .build();
    }
}

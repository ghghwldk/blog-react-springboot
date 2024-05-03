package com.m.blog.aggregate.posting.infrastructure.repository;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostingDto {
    private String postingId;
    private String title;
    private String content;
    private String boardId;
    private String boardName;
    private String boardCollectionId;
    private String boardCollectionName;
    private LocalDateTime createdTime;

    @QueryProjection
    public PostingDto(String postingId, String title, String content, String boardId, String boardName, String boardCollectionId, String boardCollectionName, LocalDateTime createdTime) {
        this.postingId = postingId;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.boardName = boardName;
        this.boardCollectionId = boardCollectionId;
        this.boardCollectionName = boardCollectionName;
        this.createdTime = createdTime;
    }
}

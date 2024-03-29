package com.m.blog.domain.posting.infrastructure.repository;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostingDto {
    private int postingId;
    private String title;
    private String content;
    private int boardId;
    private String boardName;
    private int boardCollectionId;
    private String boardCollectionName;
    private LocalDateTime createdTime;

    @QueryProjection
    public PostingDto(int postingId, String title, String content, int boardId, String boardName, int boardCollectionId, String boardCollectionName, LocalDateTime createdTime) {
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

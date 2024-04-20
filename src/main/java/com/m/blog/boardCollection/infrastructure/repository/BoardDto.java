package com.m.blog.boardCollection.infrastructure.repository;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {
    int boardCollectionId;
    String boardCollectionName;
    int boardId;
    String boardName;
    String description;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;


    @QueryProjection
    public BoardDto(int boardCollectionId, String boardCollectionName, int boardId, String boardName, String description, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.boardCollectionId = boardCollectionId;
        this.boardCollectionName = boardCollectionName;
        this.boardId = boardId;
        this.boardName = boardName;
        this.description = description;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}

package com.m.blog.aggregate.board.adapter.out.persistence;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {
    String boardCollectionId;
    String boardCollectionName;
    String boardId;
    String boardName;
    String description;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;


    @QueryProjection
    public BoardDto(String boardCollectionId, String boardCollectionName, String boardId, String boardName, String description, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.boardCollectionId = boardCollectionId;
        this.boardCollectionName = boardCollectionName;
        this.boardId = boardId;
        this.boardName = boardName;
        this.description = description;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }
}

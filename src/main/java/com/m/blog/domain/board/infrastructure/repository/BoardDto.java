package com.m.blog.domain.board.infrastructure.repository;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
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

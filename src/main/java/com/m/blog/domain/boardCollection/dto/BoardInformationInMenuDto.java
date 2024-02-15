package com.m.blog.domain.boardCollection.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardInformationInMenuDto {
    int boardCollectionId;
    String boardCollectionName;
    int boardId;
    String boardName;
    int postingCount;

    @QueryProjection
    public BoardInformationInMenuDto(int boardCollectionId, String boardCollectionName, int boardId, String boardName, String description, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.boardCollectionId = boardCollectionId;
        this.boardCollectionName = boardCollectionName;
        this.boardId = boardId;
        this.boardName = boardName;
    }
}
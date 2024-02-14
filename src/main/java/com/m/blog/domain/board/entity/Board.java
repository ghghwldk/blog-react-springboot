package com.m.blog.domain.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.time.LocalDateTime;

@IdClass(BoardId.class)
@Entity
@Table(name="board")
@Getter
@NoArgsConstructor
public class Board {
    @Id
    int id;
    @Id
    int boardCollectionId;
    String name;
    String description;
    @CreatedDate
    LocalDateTime createdTime;
    @LastModifiedDate
    LocalDateTime updatedTime;
}

package com.m.blog.domain.board.application.port.out;

import com.m.blog.global.entity.TimeComponent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@IdClass(BoardId.class)
@Entity
@Table(name="board")
@Getter
@NoArgsConstructor
public class BoardEntity extends TimeComponent {
    @Id
    int id;
    @Id
    int boardCollectionId;
    String name;
    String description;
}

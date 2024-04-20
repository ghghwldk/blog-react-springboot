package com.m.blog.boardCollection.infrastructure.repository;

import com.m.blog.global.entity.TimeComponent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="board")
@Getter
@NoArgsConstructor
public class BoardEntity extends TimeComponent {
    @Id
    String id;
    String boardCollectionId;
    String name;
    String description;
}

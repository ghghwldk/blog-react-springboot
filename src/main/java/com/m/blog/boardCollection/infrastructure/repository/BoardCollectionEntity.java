package com.m.blog.boardCollection.infrastructure.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="board_collection")
@Getter
@NoArgsConstructor
public class BoardCollectionEntity {
    @Id
    String id;
    String name;
}

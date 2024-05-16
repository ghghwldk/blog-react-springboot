package com.m.blog.aggregate.boardCollection.adapter.out.persistence;

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

package com.m.blog.domain.boardCollection.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="board_collection")
@Getter
@NoArgsConstructor
public class BoardCollection {
    @Id
    int id;
    String name;
}

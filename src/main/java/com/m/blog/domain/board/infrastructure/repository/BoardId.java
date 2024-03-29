package com.m.blog.domain.board.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardId implements Serializable {
    int id;
    int boardCollectionId;
}

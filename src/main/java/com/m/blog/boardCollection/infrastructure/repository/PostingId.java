package com.m.blog.boardCollection.infrastructure.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingId implements Serializable {
    String id;
    String boardId;
    String boardCollectionId;
}

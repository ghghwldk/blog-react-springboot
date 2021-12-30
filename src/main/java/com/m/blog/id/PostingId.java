package com.m.blog.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingId implements Serializable {
    int id;
    int boardId;
    int boardCollectionId;
}

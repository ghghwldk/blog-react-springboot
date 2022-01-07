package com.m.blog.entity;

import com.m.blog.entity.time.TimeComponent;
import com.m.blog.id.PostingId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@IdClass(PostingId.class)
@Entity
@Table(name="posting")
@Getter
@NoArgsConstructor
public class Posting extends TimeComponent {
    @Id
    int id;
    int boardId;
    int boardCollectionId;
    String title;
    String content;

    public void setContent(String content) {
        this.content = content;
    }
    public void setTitle(String title) { this.title = title; }

    @Builder
    public Posting(int id, int boardId, int boardCollectionId, String title, String content) {
        this.id = id;
        this.boardId = boardId;
        this.boardCollectionId = boardCollectionId;
        this.title = title;
        this.content = content;
    }
}
package com.m.blog.domain.posting.infrastructure.repository;

import com.m.blog.global.entity.TimeComponent;
import lombok.AllArgsConstructor;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostingEntity extends TimeComponent {
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


}
package com.m.blog.aggregate.posting.adapter.out.persistence;

import com.m.blog.global.entity.TimeComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="posting")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostingEntity extends TimeComponent {
    @Id String id;
    String boardId;
    String title;
    String content;
}
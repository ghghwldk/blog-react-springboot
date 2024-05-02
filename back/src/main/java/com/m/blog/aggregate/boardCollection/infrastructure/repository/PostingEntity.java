package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import com.m.blog.aggregate.file.infrastructure.repository.FileEntity;
import com.m.blog.global.entity.TimeComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="posting_id")
    private List<FileEntity> fileEntities= new ArrayList<>();
}
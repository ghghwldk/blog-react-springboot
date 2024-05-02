package com.m.blog.aggregate.file.infrastructure.repository;

import com.m.blog.aggregate.boardCollection.infrastructure.repository.PostingEntity;
import com.m.blog.global.entity.TimeComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="file")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileEntity extends TimeComponent {
    @Id
    String id;
    String originalFileName;
    String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id", referencedColumnName = "id")
    private PostingEntity postingEntity;
}
package com.m.blog.file.infrastructure.repository;

import com.m.blog.file.application.domain.UploadedFile;
import com.m.blog.global.entity.TimeComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="file")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileEntity extends TimeComponent {
    @Id
    String id;
    String postingId;
    String originalFileName;
    String filePath;
}
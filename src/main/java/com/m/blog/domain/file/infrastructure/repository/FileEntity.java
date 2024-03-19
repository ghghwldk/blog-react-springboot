package com.m.blog.domain.file.infrastructure.repository;

import com.m.blog.domain.file.application.domain.UploadFile;
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
    String assignedFileName;
    String originalFileName;
    String filePath;

    public static FileEntity of(UploadFile vo){
        return FileEntity.builder()
                .assignedFileName(vo.getAssignedFileName())
                .originalFileName(vo.getOriginalFileName())
                .filePath(vo.getDirectoryName())
                .build();
    }

}
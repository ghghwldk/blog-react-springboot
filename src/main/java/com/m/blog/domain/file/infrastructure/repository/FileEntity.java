package com.m.blog.domain.file.infrastructure.repository;

import com.m.blog.domain.file.domain.UploadFile;
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
    String fileName;
    String originalFileName;
    String filePath;

    public static FileEntity of(UploadFile vo, String directoryName){
        return FileEntity.builder()
                .fileName(vo.getSavedFileName())
                .originalFileName(vo.getOriginalFileName())
                .filePath(directoryName)
                .build();
    }

}
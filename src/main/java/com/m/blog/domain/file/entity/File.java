package com.m.blog.domain.file.entity;

import com.m.blog.domain.file.vo.FileVo;
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
public class File extends TimeComponent {
    @Id
    String fileName;
    String originalFileName;
    String filePath;

    public static File of(FileVo vo, String directoryName){
        return com.m.blog.domain.file.entity.File.builder()
                .fileName(vo.getSavedFileName())
                .originalFileName(vo.getOriginalFileName())
                .filePath(directoryName)
                .build();
    }

}
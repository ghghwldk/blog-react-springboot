package com.m.blog.domain.file.entity;

import com.m.blog.global.entity.TimeComponent;
import lombok.AllArgsConstructor;
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
public class File extends TimeComponent {
    @Id
    String fileName;
    String originalFileName;
    String filePath;

}
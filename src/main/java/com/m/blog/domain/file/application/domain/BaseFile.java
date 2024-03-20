package com.m.blog.domain.file.application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@Getter
public class BaseFile {
    protected String assignedFileName;
    protected String originalFileName;
    protected String directoryName;
}

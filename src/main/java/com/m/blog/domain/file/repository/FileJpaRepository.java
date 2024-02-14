package com.m.blog.domain.file.repository;

import com.m.blog.domain.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileJpaRepository extends JpaRepository<File, String> {
    File findByFileName(String fileName);
}

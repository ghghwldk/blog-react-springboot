package com.m.blog.domain.file.repository;

import com.m.blog.domain.file.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileJpaRepository extends JpaRepository<FileEntity, String> {
    Optional<FileEntity> findByFileName(String fileName);
}

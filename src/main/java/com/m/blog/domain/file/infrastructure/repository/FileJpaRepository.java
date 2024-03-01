package com.m.blog.domain.file.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileJpaRepository extends JpaRepository<FileEntity, String> {
    Optional<FileEntity> findByFileName(String fileName);
}

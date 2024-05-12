package com.m.blog.aggregate.file.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileJpaRepository extends JpaRepository<FileEntity, String> {
    List<FileEntity> findAllByPostingId(String postingId);
}

package com.m.blog.aggregate.file.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileJpaRepository extends JpaRepository<FileEntity, String> {
    List<FileEntity> findAllByPostingId(String postingId);
}

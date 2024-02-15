package com.m.blog.domain.file.repository;

import com.m.blog.domain.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileJpaRepository extends JpaRepository<File, String> {
    Optional<File> findByFileName(String fileName);
}

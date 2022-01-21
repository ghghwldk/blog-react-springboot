package com.m.blog.repository.jpa;

import com.m.blog.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileJpaRepository extends JpaRepository<File, String> {
    File findByFileName(String fileName);
}

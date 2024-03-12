package com.m.blog.domain.loadfiletoawscloud.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface FileEntityRepository2 extends JpaRepository<FileEntity2, Integer> {

    @Transactional(readOnly = true)
    Optional<FileEntity2> findFirstByName(String fileName);

    //boolean existsByName(String fileName);
}

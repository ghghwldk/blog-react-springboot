package com.m.blog.domain.loadfiletoawscloud.infra.repository;

import com.m.blog.domain.loadfiletoawscloud.infra.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RepositoryFile extends JpaRepository<FileEntity, Integer> {

    @Transactional(readOnly = true)
    Optional<FileEntity> findFirstByName(String fileName);

    //boolean existsByName(String fileName);
}

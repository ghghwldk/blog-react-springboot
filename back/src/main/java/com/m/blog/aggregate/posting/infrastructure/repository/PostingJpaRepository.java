package com.m.blog.aggregate.posting.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingJpaRepository extends JpaRepository<PostingEntity, String>{
    List<PostingEntity> findAllByBoardIdIn(List<String> boardIds);
}
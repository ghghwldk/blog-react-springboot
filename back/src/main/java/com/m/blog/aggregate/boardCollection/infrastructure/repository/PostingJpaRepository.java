package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostingJpaRepository extends JpaRepository<PostingEntity, String>{
    List<PostingEntity> findAllByBoardIdIn(List<String> boardIds);
}
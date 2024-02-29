package com.m.blog.domain.posting.repository;

import com.m.blog.domain.posting.entity.PostingEntity;
import com.m.blog.domain.posting.entity.PostingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostingJpaRepository extends JpaRepository<PostingEntity, PostingId>{
    Optional<PostingEntity> findByBoardCollectionIdAndBoardIdAndId(Integer boardCollectionId, Integer boardId, Integer id);
}
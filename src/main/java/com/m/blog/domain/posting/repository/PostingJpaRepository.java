package com.m.blog.domain.posting.repository;

import com.m.blog.domain.posting.entity.Posting;
import com.m.blog.domain.posting.entity.PostingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostingJpaRepository extends JpaRepository<Posting, PostingId>{
    Optional<Posting> findByBoardCollectionIdAndBoardIdAndId(Integer boardCollectionId, Integer boardId, Integer id);
}
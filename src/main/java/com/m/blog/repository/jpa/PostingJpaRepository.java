package com.m.blog.repository.jpa;

import com.m.blog.entity.File;
import com.m.blog.entity.Posting;
import com.m.blog.id.PostingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingJpaRepository extends JpaRepository<Posting, PostingId>{
    Posting findByBoardCollectionIdAndBoardIdAndId(Integer boardCollectionId, Integer boardId, Integer id);
}
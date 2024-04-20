package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import com.m.blog.aggregate.boardCollection.application.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardJpaRepository extends JpaRepository<BoardEntity, String> {
    List<BoardEntity> findAllByBoardCollectionId(String boardCollectionId);
}

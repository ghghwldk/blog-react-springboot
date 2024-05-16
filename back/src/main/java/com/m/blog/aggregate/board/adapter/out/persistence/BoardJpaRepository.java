package com.m.blog.aggregate.board.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardJpaRepository extends JpaRepository<BoardEntity, String> {
    List<BoardEntity> findAllByBoardCollectionId(String boardCollectionId);
}

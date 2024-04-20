package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardCollectionJpaRepository extends JpaRepository<BoardCollectionEntity, Integer> {
}

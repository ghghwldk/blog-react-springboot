package com.m.blog.aggregate.boardCollection.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardCollectionJpaRepository extends JpaRepository<BoardCollectionEntity, String> {
}

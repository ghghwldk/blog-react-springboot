package com.m.blog.domain.boardCollection.repository;

import com.m.blog.domain.boardCollection.entity.BoardCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BoardCollectionJpaRepository extends JpaRepository<BoardCollection, Integer> {
    List<BoardCollection> findAll();
}

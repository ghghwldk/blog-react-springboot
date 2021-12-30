package com.m.blog.repository;

import com.m.blog.entity.BoardCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardCollectionJpaRepository extends JpaRepository<BoardCollection, Integer> {
    BoardCollection findById(int id);
    List<BoardCollection> findAll();
}

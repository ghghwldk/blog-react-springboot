package com.m.blog.domain.boardCollection.repository;

import com.m.blog.domain.boardCollection.entity.BoardCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardCollectionRepository extends JpaRepository<BoardCollection, Integer> {
}

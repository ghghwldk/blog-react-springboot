package com.m.blog.boardCollection.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostingJpaRepository extends JpaRepository<PostingEntity, String>{

}
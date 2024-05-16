package com.m.blog.aggregate.posting.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingJpaRepository extends JpaRepository<PostingEntity, String>{
}
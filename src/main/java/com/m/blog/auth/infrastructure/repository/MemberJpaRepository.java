package com.m.blog.auth.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, String> {
    Optional<MemberEntity> findMemberByIdAndPassword(String id, String password);
}

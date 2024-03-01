package com.m.blog.domain.auth.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, String> {
    Optional<MemberEntity> findMemberByIdAndPassword(String id, String password);
}

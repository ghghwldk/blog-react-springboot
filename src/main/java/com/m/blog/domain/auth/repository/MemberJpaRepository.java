package com.m.blog.domain.auth.repository;

import com.m.blog.domain.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, String> {
    Optional<Member> findMemberByIdAndPassword(String id, String password);
}

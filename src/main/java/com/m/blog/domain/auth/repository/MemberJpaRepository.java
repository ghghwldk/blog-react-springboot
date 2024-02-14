package com.m.blog.domain.auth.repository;

import com.m.blog.domain.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, String> {
    Member findMemberByIdAndPassword(String id, String password);
}

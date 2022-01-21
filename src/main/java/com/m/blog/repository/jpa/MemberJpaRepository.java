package com.m.blog.repository.jpa;

import com.m.blog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, String> {
    Member findMemberByIdAndPassword(String id, String password);
}

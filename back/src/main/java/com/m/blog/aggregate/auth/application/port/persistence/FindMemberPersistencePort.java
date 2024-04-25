package com.m.blog.aggregate.auth.application.port.persistence;

import com.m.blog.aggregate.auth.application.domain.Member;

import java.util.Optional;

public interface FindMemberPersistencePort {
    Optional<Member> find(Member member);
}

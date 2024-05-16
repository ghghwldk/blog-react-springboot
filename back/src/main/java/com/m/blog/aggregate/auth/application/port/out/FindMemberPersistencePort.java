package com.m.blog.aggregate.auth.application.port.out;

import com.m.blog.aggregate.auth.application.domain.Member;

import java.util.Optional;

public interface FindMemberPersistencePort {
    Optional<Member> find(String userId);
}

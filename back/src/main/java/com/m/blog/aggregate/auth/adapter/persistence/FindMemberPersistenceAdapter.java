package com.m.blog.aggregate.auth.adapter.persistence;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.application.port.persistence.FindMemberPersistencePort;
import com.m.blog.aggregate.auth.infrastructure.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class FindMemberPersistenceAdapter implements FindMemberPersistencePort {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> find(Member.LoginInfo loginInfo){
        return memberJpaRepository
                .findById(loginInfo.getMemberId().getValue())
                .map(MemberPersistenceMapper::of);
    }
}

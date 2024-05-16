package com.m.blog.aggregate.auth.adapter.out.persistence;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.auth.application.domain.Member;
import com.m.blog.aggregate.auth.application.port.out.FindMemberPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class FindMemberPersistenceAdapter implements FindMemberPersistencePort {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> find(Member member){
        return memberJpaRepository
                .findById(member.getMemberId().getValue())
                .map(MemberPersistenceMapper::of);
    }
}

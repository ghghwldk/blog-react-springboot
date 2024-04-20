package com.m.blog.auth.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.auth.application.domain.Member;
import com.m.blog.auth.application.port.persistence.FindMemberPersistencePort;
import com.m.blog.auth.infrastructure.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@Adapter
@RequiredArgsConstructor
public class FindMemberPersistenceAdapter implements FindMemberPersistencePort {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member find(Member.LoginInfo loginInfo){
        return memberJpaRepository
                .findMemberByIdAndPassword(
                        loginInfo.getMemberId().getValue(), loginInfo.getPassword()
                )
                .map(MemberPersistenceMapper::of)
                .orElseThrow(EntityNotFoundException::new);
    }
}

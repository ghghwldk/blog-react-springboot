package com.m.blog.domain.auth.adapter.persistence;

import com.m.blog.common.Adapter;
import com.m.blog.domain.auth.application.domain.Member;
import com.m.blog.domain.auth.application.port.persistence.FindMemberPort;
import com.m.blog.domain.auth.infrastructure.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@Adapter
@RequiredArgsConstructor
public class FindMemberAdapter implements FindMemberPort {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member find(Member.LoginInfo loginInfo){
        return memberJpaRepository
                .findMemberByIdAndPassword(
                        loginInfo.getId().getUserId(), loginInfo.getPassword()
                )
                .map(MemberJpaMapper::of)
                .orElseThrow(EntityNotFoundException::new);
    }
}

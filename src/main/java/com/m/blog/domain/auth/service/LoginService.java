package com.m.blog.domain.auth.service;


import com.m.blog.domain.auth.entity.Member;
import com.m.blog.domain.auth.repository.MemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    public Member checkMemberOrNot(String id, String password){
        return memberJpaRepository.findMemberByIdAndPassword(id, password);
    }
}

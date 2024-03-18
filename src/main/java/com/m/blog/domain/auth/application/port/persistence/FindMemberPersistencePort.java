package com.m.blog.domain.auth.application.port.persistence;

import com.m.blog.domain.auth.application.domain.Member;

public interface FindMemberPersistencePort {
    Member find(Member.LoginInfo loginInfo);
}

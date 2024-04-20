package com.m.blog.auth.application.port.persistence;

import com.m.blog.auth.application.domain.Member;

public interface FindMemberPersistencePort {
    Member find(Member.LoginInfo loginInfo);
}

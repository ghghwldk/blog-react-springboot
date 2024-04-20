package com.m.blog.aggregate.auth.application.port.persistence;

import com.m.blog.aggregate.auth.application.domain.Member;

public interface FindMemberPersistencePort {
    Member find(Member.LoginInfo loginInfo);
}

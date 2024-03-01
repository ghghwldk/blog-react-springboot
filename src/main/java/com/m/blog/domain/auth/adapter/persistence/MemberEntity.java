package com.m.blog.domain.auth.adapter.persistence;

import com.m.blog.global.entity.TimeComponent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
@Getter
@NoArgsConstructor
class MemberEntity extends TimeComponent {
    @Id
    String id;
    String name;
    String password;
    String role;
}
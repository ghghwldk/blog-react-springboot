package com.m.blog.domain.auth.infrastructure.repository;

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
public class MemberEntity extends TimeComponent {
    @Id
    String id;
    String name;
    String password;
    String role;
}
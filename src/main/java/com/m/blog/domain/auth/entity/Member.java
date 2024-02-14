package com.m.blog.domain.auth.entity;

import com.m.blog.global.entity.TimeComponent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="member")
@Getter
@NoArgsConstructor
public class Member extends TimeComponent {
    @Id
    String id;
    String name;
    String password;
    String role;
}
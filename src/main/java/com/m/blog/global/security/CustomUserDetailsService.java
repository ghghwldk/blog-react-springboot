package com.m.blog.global.security;

import com.m.blog.domain.auth.infrastructure.repository.MemberEntity;
import com.m.blog.domain.auth.infrastructure.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity found = memberJpaRepository.findById(username)
                .orElseThrow(EntityNotFoundException::new);

        return createUserDetails(found);
    }

    // If the User value exists in the DB, create a UserDetails object and return it
    private UserDetails createUserDetails(MemberEntity member) {
        GrantedAuthority grantedAuthority =
                new SimpleGrantedAuthority(member.getRole());

        return new User(
                member.getId().toString(),
                member.getPassword(),
                Set.of(grantedAuthority)
        );
    }
}


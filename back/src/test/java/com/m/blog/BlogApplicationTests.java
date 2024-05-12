package com.m.blog;

import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardCollectionJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootTest
class BlogApplicationTests {
	@Autowired
	BoardCollectionJpaRepository boardCollectionJpaRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		String password = passwordEncoder.encode("ghma");
		boolean isMatched = passwordEncoder.matches(
				"ghma",
				passwordEncoder.encode("ghma")
		);
		isMatched = isMatched;
	}

	@Test
	void contextLoads2() {

	}

}



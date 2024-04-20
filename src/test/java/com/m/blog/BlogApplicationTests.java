package com.m.blog;

import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardCollectionJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BlogApplicationTests {
	@Autowired
	BoardCollectionJpaRepository boardCollectionJpaRepository;
	@Test
	void contextLoads() {

	}

}

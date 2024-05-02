package com.m.blog;

import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardCollectionJpaRepository;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.PostingEntity;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.PostingJpaRepository;
import com.m.blog.aggregate.file.infrastructure.repository.FileEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

	@Autowired
	PostingJpaRepository postingJpaRepository;
	@Test
	@Transactional
	void test(){
		PostingEntity postingEntity = postingJpaRepository.findById("7184122073848090632").orElseThrow();
		List<FileEntity> fileEntityList = postingEntity.getFileEntities();

		fileEntityList = fileEntityList;
	}
}

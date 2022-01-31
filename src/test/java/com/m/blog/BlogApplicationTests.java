package com.m.blog;

import com.m.blog.dto.BoardInformationInMenuDto;
import com.m.blog.repository.jpa.BoardCollectionJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {
	@Autowired
	BoardCollectionJpaRepository boardCollectionJpaRepository;
	@Test
	void contextLoads() {

	}

}

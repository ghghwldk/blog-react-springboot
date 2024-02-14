package com.m.blog.domain.boardCollection.repository;

import com.m.blog.domain.boardCollection.repository.BoardCollectionRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class BoardCollectionRepositoryImpl implements BoardCollectionRepository {
    @Autowired
    JPAQueryFactory query;

}

package com.m.blog.repository;

import com.m.blog.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.m.blog.entity.QBoard.board;


@Repository
public class BoardCollectionRepositoryImpl implements BoardCollectionRepository{
    @Autowired
    JPAQueryFactory query;

}

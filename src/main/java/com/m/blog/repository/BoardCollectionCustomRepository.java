package com.m.blog.repository;

import com.m.blog.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.m.blog.entity.QBoard.board;

@Repository
public class BoardCollectionCustomRepository {
    @Autowired
    JPAQueryFactory query;

    public List<Board> findBoards(int boardCollectionId){
        return query.selectFrom(board)
                .orderBy(board.boardCollectionId.asc(),
                        board.id.desc())
                .where(board.boardCollectionId.eq(boardCollectionId))
                .fetch();
    }
}

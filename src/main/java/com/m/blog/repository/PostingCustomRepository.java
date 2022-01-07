package com.m.blog.repository;


import com.m.blog.dto.LatestPostingDto;
import com.m.blog.dto.QLatestPostingDto;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.m.blog.entity.QBoard.board;
import static com.m.blog.entity.QBoardCollection.boardCollection;
import static com.m.blog.entity.QPosting.posting;

@Repository
public class PostingCustomRepository {

    @Autowired
    JPAQueryFactory query;


    @Transactional
    public int findNewId(int boardCollectionId, int boardId){
        int count= (int) query.selectFrom(posting)
                .fetchCount();

        return count+1;
    }

    public Page<LatestPostingDto> getPageOfLatestPosting(String condition, Pageable pageable){
        List<LatestPostingDto> fetch=
                query.select(
                        new QLatestPostingDto(
                                posting.id,
                                posting.title,
                                posting.content,
                                board.id,
                                board.name,
                                boardCollection.id,
                                boardCollection.name,
                                posting.createdTime
                        ))
                        .from(posting)
                        .join(board).on(posting.boardId.eq(board.id),
                        posting.boardCollectionId.eq(board.boardCollectionId))
                        .join(boardCollection).on(posting.boardCollectionId.eq(boardCollection.id))
                        .orderBy(posting.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        JPAQuery<LatestPostingDto> count=
                query.select(
                        new QLatestPostingDto(
                                posting.id,
                                posting.title,
                                posting.content,
                                board.id,
                                board.name,
                                boardCollection.id,
                                boardCollection.name,
                                posting.createdTime
                        ))
                        .from(posting)
                        .join(board).on(posting.boardId.eq(board.id),
                        posting.boardCollectionId.eq(board.boardCollectionId))
                        .join(boardCollection).on(posting.boardCollectionId.eq(boardCollection.id))
                        .orderBy(posting.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }


    public Page<LatestPostingDto> getPageOfPosting(int boardCollectionId, int boardId,String condition, Pageable pageable){
        List<LatestPostingDto> fetch=
                query.select(
                        new QLatestPostingDto(
                                posting.id,
                                posting.title,
                                posting.content,
                                board.id,
                                board.name,
                                boardCollection.id,
                                boardCollection.name,
                                posting.createdTime
                        ))
                        .from(posting)
                        .join(board).on(posting.boardId.eq(board.id),
                        posting.boardCollectionId.eq(board.boardCollectionId))
                        .join(boardCollection).on(posting.boardCollectionId.eq(boardCollection.id))
                        .where(posting.boardCollectionId.eq(boardCollectionId), posting.boardId.eq(boardId))
                        .orderBy(posting.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        JPAQuery<LatestPostingDto> count=
                query.select(
                        new QLatestPostingDto(
                                posting.id,
                                posting.title,
                                posting.content,
                                board.id,
                                board.name,
                                boardCollection.id,
                                boardCollection.name,
                                posting.createdTime
                        ))
                        .from(posting)
                        .join(board).on(posting.boardId.eq(board.id),
                        posting.boardCollectionId.eq(board.boardCollectionId))
                        .join(boardCollection).on(posting.boardCollectionId.eq(boardCollection.id))
                        .where(posting.boardCollectionId.eq(boardCollectionId), posting.boardId.eq(boardId))
                        .orderBy(posting.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }
}


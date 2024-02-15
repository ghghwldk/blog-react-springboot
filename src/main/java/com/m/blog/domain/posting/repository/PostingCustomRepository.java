package com.m.blog.domain.posting.repository;


import com.m.blog.domain.board.entity.QBoard;
import com.m.blog.domain.boardCollection.entity.QBoardCollection;
import com.m.blog.domain.posting.dto.PostingDto;
import com.m.blog.domain.posting.dto.QPostingDto;
import com.m.blog.domain.posting.entity.QPosting;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.m.blog.domain.board.entity.QBoard.board;
import static com.m.blog.domain.boardCollection.entity.QBoardCollection.boardCollection;
import static com.m.blog.domain.posting.entity.QPosting.posting;


@Repository
public class PostingCustomRepository {

    @Autowired
    JPAQueryFactory query;


    @Transactional
    public int findNewId(int boardCollectionId, int boardId){
        int count= (int) query.selectFrom(posting)
                .where(posting.boardCollectionId.eq(boardCollectionId),
                        posting.boardId.eq(boardId))
                .fetchCount();

        return count+1;
    }

    public Page<PostingDto> getPageOfLatestPosting(Pageable pageable){
        List<PostingDto> fetch=
                query.select(
                        new QPostingDto(
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

        JPAQuery<PostingDto> count=
                query.select(
                        new QPostingDto(
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

    public PostingDto getPosting(int boardCollectionId, int boardId, int postingId){
        QPosting p = new QPosting("p");
        QBoardCollection bc = new QBoardCollection("bc");
        QBoard b = new QBoard("b");
        PostingDto fetch=
                query.select(
                        new QPostingDto(
                                p.id,
                                p.title,
                                p.content,
                                b.id,
                                b.name,
                                bc.id,
                                bc.name,
                                p.createdTime
                        ))
                        .from(p)
                        .join(b).on(p.boardId.eq(b.id),
                        p.boardCollectionId.eq(b.boardCollectionId))
                        .join(bc).on(p.boardCollectionId.eq(bc.id))
                        .where(bc.id.eq(boardCollectionId), b.id.eq(boardId), p.id.eq(postingId))
                        .fetchOne();
        return fetch;
    }

    public Page<PostingDto> getPageOfPosting(int boardCollectionId, int boardId, Pageable pageable){
        List<PostingDto> fetch=
                query.select(
                        new QPostingDto(
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

        JPAQuery<PostingDto> count=
                query.select(
                        new QPostingDto(
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

